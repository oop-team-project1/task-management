package taskmanagement.commands.creation;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.BoardImpl;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.models.tasks.enums.story.StoryStatus;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class CreateStory implements Command {

    // TODO make a story with 1 argument less? Create story without member

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;
    private static final String INVALID_PRIORITY = "Invalid priority type!";
    private static final String INVALID_SIZE = "Invalid size type!";
    private static final String INVALID_STATUS = "Invalid status type!";
    private final TaskManagementRepository taskManagementRepository;

    private Board board;
    private Member member;

    private String boardName;

    private String title;
    private String description;
    private String memberName;
    private Priority priority;
    private Size size;
    private StoryStatus status;

    public CreateStory(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        board = taskManagementRepository.findBoardByName(boardName);
        member = taskManagementRepository.findMemberByName(memberName);

        Story createStory = taskManagementRepository.createNewStoryWithMember(title, description, priority, size, member,status);

        return String.format(CommandsConstants.STORY_CREATED_MESSAGE, createStory.getId());
    }

    private void parseParameters(List<String> parameters) {

        boardName = parameters.get(0);

        //TODO why don't we use parser helpers for the title? or other fields? I can input something else
        title = parameters.get(1);
        description = parameters.get(2);
        memberName = parameters.get(3);
        priority = ParsingHelpers.tryParseEnum(parameters.get(4), Priority.class, INVALID_PRIORITY);
        size = ParsingHelpers.tryParseEnum(parameters.get(5), Size.class, INVALID_SIZE);
        status = ParsingHelpers.tryParseEnum(parameters.get(6), StoryStatus.class, INVALID_STATUS);

    }


}
