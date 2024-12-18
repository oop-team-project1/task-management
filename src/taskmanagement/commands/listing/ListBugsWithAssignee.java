package taskmanagement.commands.listing;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.BoardImpl;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.contracts.Assignable;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.enums.bug.BugStatus;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListBugsWithAssignee implements Command {

    // parameters:
    // none -> return all assigned tasks
    // <assignee> + a / -> return all tasks assigned to
    // <status> s/ -> return all tasks with status <status>
    // sort


    public static final String TASK_ERROR_MESSAGE = "There are no registered stories!";
    public static final String PARAMETERS_COUNT_ERROR_MESSAGE = "Wrong parameters count!";
    public static final int PARAMETERS_COUNT_MIN = 0;
    public static final int PARAMETERS_COUNT_FILTER_ONCE = 2;
    public static final int PARAMETERS_COUNT_FILTER_TWICE = 4;
    public static final int PARAMETERS_COUNT_MAX = 5;

    private List<Bug> bugs;
    private StringBuilder result = new StringBuilder("Assigned Bugs");


    public ListBugsWithAssignee(TaskManagementRepository taskManagementRepository) {
        bugs = taskManagementRepository.getBugs().stream().filter(Assignable::isAssigned).collect(Collectors.toList());
    }


    @Override
    public String execute(List<String> parameters) {
        if (bugs.isEmpty()) {
            throw new IllegalArgumentException(TASK_ERROR_MESSAGE);
        }

        ValidationHelper.validateValueEitherOfNumbers(parameters.size(), PARAMETERS_COUNT_ERROR_MESSAGE, PARAMETERS_COUNT_MIN, PARAMETERS_COUNT_FILTER_ONCE, PARAMETERS_COUNT_FILTER_TWICE, PARAMETERS_COUNT_MAX);

        if (parameters.isEmpty()) {
            result.append(":\n");
            getFinalResult();
            return result.toString();

        }

        if (parameters.contains("a")) {
            String assigneeName = parameters.get(parameters.indexOf("a") - 1);
            ValidationHelper.validateStringLength(assigneeName, BoardImpl.NAME_MIN_LENGTH, BoardImpl.NAME_MAX_LENGTH, MemberImpl.NAME_LENGTH_ERR);
            filterTasksByAssignee(assigneeName);
            result.append(String.format(" to %s", assigneeName));
        }

        if (parameters.contains("s")) {
            String bugStatus = parameters.get(parameters.indexOf("s") - 1);
            BugStatus status = ParsingHelpers.tryParseBugStatus(bugStatus);
            filterBugsByStatus(status);
            result.append(String.format(" filtered by status %s", status));
        }

        if (parameters.contains("sort")) {
            sortBugsByTitle();
            result.append(" sorted by title");
        }

        result.append(":\n");
        getFinalResult();

        return result.toString();
    }


    private void filterTasksByAssignee(String assigneeName) {
        bugs = bugs.stream().filter(bug -> bug.getAssignee().getName().equals(assigneeName)).collect(Collectors.toList());

    }

    private void filterBugsByStatus(BugStatus status) {
        bugs = bugs.stream().filter(bug -> bug.getStatus().equals(status)).collect(Collectors.toList());

    }

    private void sortBugsByTitle() {
        bugs = bugs.stream().sorted(Comparator.comparing(Bug::getTitle)).collect(Collectors.toList());
    }

    private void getFinalResult() {

        bugs.forEach(bug -> result.append("Bug ID ").append(bug.getId()).append(" Title: ").append(bug.getTitle()).append("\n"));

    }
}
