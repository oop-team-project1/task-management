package taskmanagement.commands.modifying;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class RevertStatus implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    private int taskId;

    public RevertStatus(TaskManagementRepository taskManagementRepository)
    {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Task task = taskManagementRepository.findTaskById(taskId);
        task.advanceStatus();

        return String.format(CommandsConstants.STATUS_REVERTED, task.getId(), task.currentStatus());

    }

    private void parseParameters(List<String> parameters)
    {
        taskId = ParsingHelpers.tryParseInteger(parameters.get(0), "task id");
    }
}
