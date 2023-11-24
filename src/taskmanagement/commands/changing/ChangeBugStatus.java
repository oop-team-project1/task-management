package taskmanagement.commands.changing;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.enums.bug.BugStatus;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ChangeBugStatus implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String ERROR_MESSAGE = "Invalid status type!";
    private final TaskManagementRepository taskManagementRepository;
    private int bugId;
    private BugStatus status;

    public ChangeBugStatus(TaskManagementRepository taskManagementRepository)
    {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters)
    {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Bug bug = taskManagementRepository.findBugById(bugId);
        bug.changeStatus(status);

        return String.format(CommandsConstants.BUG_STATUS_CHANGED_MESSAGE, bug.getId(),status);
    }

    private void parseParameters(List<String> parameters)
    {
        bugId = ParsingHelpers.tryParseInteger(parameters.get(0), "bug id");
        status = ParsingHelpers.tryParseBugStatus(parameters.get(1));
    }
}
