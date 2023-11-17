package taskmanagement.commands.changing;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ChangeBugPriority implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String ERROR_MESSAGE = "Invalid priority type!";
    private final TaskManagementRepository taskManagementRepository;
    private int bugId;
    private Priority priority;

    public ChangeBugPriority(TaskManagementRepository taskManagementRepository)
    {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters)
    {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Bug bug = taskManagementRepository.findBugById(bugId);
        bug.changePriority(priority);

        return String.format(CommandsConstants.BUG_PRIORITY_CHANGED_MESSAGE, bug.getId());
    }

    private void parseParameters(List<String> parameters)
    {
        bugId = ParsingHelpers.tryParseInteger(parameters.get(0), "bug id");
        priority = ParsingHelpers.tryParseEnum(parameters.get(1), priority.getDeclaringClass(), ERROR_MESSAGE);
    }
}
