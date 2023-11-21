package taskmanagement.commands.modifying;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class UnassignBug implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final TaskManagementRepository taskManagementRepository;
    private final String INVALID_ID = "Please provide a valid bug id!";

    private int taskId;
    private String member;


    public UnassignBug(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Bug bug = taskManagementRepository.findBugById(taskId, INVALID_ID);
        Member memberToUnassign = taskManagementRepository.findMemberByName(member);

        bug.removeAssignee(memberToUnassign);
        memberToUnassign.removeTask(bug);


        return String.format(CommandsConstants.MEMBER_ASSIGNED, member, bug.getId());
    }

    private void parseParameters(List<String> parameters) {
        taskId = ParsingHelpers.tryParseInteger(parameters.get(0), "task id");
        member = parameters.get(1);

    }
}
