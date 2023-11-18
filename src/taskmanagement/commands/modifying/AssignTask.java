package taskmanagement.commands.modifying;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class AssignTask implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final TaskManagementRepository taskManagementRepository;
    private final String TYPE_NOT_ASSIGNABLE = "The task you provided is not assignable!";

    private int taskId;
    private String member;


    public AssignTask(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Task task = taskManagementRepository.findTaskById(taskId);
        if (!(task instanceof Bug) || !(task instanceof Story)) {
            throw new IllegalArgumentException(TYPE_NOT_ASSIGNABLE);
        }

        Member assigneeToSet = taskManagementRepository.findMemberByName(member);

        if (task instanceof Bug) ((Bug) task).setAssignee(assigneeToSet);
        if (task instanceof Story) ((Story) task).setAssignee(assigneeToSet);


        return String.format(CommandsConstants.MEMBER_ASSIGNED, member, task.getId());
    }

    private void parseParameters(List<String> parameters) {
        taskId = ParsingHelpers.tryParseInteger(parameters.get(0), "task id");
        member = parameters.get(1);

    }
}
