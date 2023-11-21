package taskmanagement.commands.modifying;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class UnassignStory implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final TaskManagementRepository taskManagementRepository;
    private final String INVALID_ID = "Please provide a valid bug id!";

    private int taskId;
    private String member;


    public UnassignStory(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Story story = taskManagementRepository.findStoryById(taskId, INVALID_ID);
        Member memberToUnassign = taskManagementRepository.findMemberByName(member);

        story.removeAssignee(memberToUnassign);
        memberToUnassign.removeTask(story);


        return String.format(CommandsConstants.MEMBER_UNASSIGNED_STORY, member, story.getId());
    }

    private void parseParameters(List<String> parameters) {
        taskId = ParsingHelpers.tryParseInteger(parameters.get(0), "task id");
        member = parameters.get(1);

    }

}
