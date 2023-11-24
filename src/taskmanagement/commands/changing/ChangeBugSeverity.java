package taskmanagement.commands.changing;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ChangeBugSeverity implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String ERROR_MESSAGE = "Invalid severity type!";
    private final TaskManagementRepository taskManagementRepository;
    private int bugId;
    private Severity severity;

    public ChangeBugSeverity(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Bug bug = taskManagementRepository.findBugById(bugId);
        bug.changeSeverity(severity);

        return String.format(CommandsConstants.BUG_SEVERITY_CHANGED_MESSAGE, bug.getId(), severity);
    }

    private void parseParameters(List<String> parameters) {
        bugId = ParsingHelpers.tryParseInteger(parameters.get(0), "bug id");
        severity = ParsingHelpers.tryParseEnum(parameters.get(1), severity.getDeclaringClass(), ERROR_MESSAGE);
    }
}
