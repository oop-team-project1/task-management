package taskmanagement.commands.modifying;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class AssignStory implements Command {
    public final String INVALID_ID = "Please provide a valid story id!";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final TaskManagementRepository taskManagementRepository;
    private int taskId;
    private String member;


    public AssignStory(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Story story = taskManagementRepository.findStoryById(taskId, INVALID_ID);
        Member memberToBeAssigned = taskManagementRepository.findMemberByName(member);

        story.setAssignee(memberToBeAssigned);
        memberToBeAssigned.addTask(story);

        return String.format(CommandsConstants.MEMBER_ASSIGNED_STORY, member, story.getId());
    }

    private void parseParameters(List<String> parameters) {
        taskId = ParsingHelpers.tryParseInteger(parameters.get(0), "task id");
        member = parameters.get(1);

    }
}
