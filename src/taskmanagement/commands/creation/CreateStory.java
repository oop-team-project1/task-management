package taskmanagement.commands.creation;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;


import java.util.List;

public class CreateStory implements Command {


    private static final int MAX_NUMBER_OF_ARGUMENTS = 6;
    private static final int MIN_NUMBER_OF_ARGUMENTS = 5;
    private static final String INVALID_PRIORITY = "Invalid priority type!";
    private static final String INVALID_SIZE = "Invalid size type!";
    private static final String INVALID_ARGUMENTS_SIZE = "Invalid number of arguments!";
    private final TaskManagementRepository taskManagementRepository;

    private Board board;
    private Member member;

    private String boardName;

    private String title;
    private String description;
    private String memberName;
    private Priority priority;
    private Size size;

    public CreateStory(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateValueInRange(parameters.size(), MIN_NUMBER_OF_ARGUMENTS, MAX_NUMBER_OF_ARGUMENTS, INVALID_ARGUMENTS_SIZE);
        int parametersCount = parameters.size();
        parseParameters(parameters, parametersCount);

        board = taskManagementRepository.findBoardByName(boardName);
        Story createStory;
        if (parametersCount == MAX_NUMBER_OF_ARGUMENTS) {
            member = taskManagementRepository.findMemberByName(memberName);
            createStory = taskManagementRepository.createNewStoryWithMember(title, description, member, priority, size);
        } else {
            createStory = taskManagementRepository.createNewStoryWithoutMember(title, description, priority, size);
        }
        board.addTask(createStory,"story");
        return String.format(CommandsConstants.STORY_CREATED_MESSAGE, createStory.getId());
    }

    private void parseParameters(List<String> parameters, int paramCount) {

        if (paramCount == MAX_NUMBER_OF_ARGUMENTS) {
            parseWithMember(parameters);
        } else {
            parseWithoutMember(parameters);
        }
    }

    private void parseWithMember(List<String> parameters) {
        boardName = parameters.get(0);
        title = parameters.get(1);
        description = parameters.get(2);
        memberName = parameters.get(3);
        priority = ParsingHelpers.tryParseEnum(parameters.get(4), Priority.class, INVALID_PRIORITY);
        size = ParsingHelpers.tryParseEnum(parameters.get(5), Size.class, INVALID_SIZE);
    }

    private void parseWithoutMember(List<String> parameters) {
        boardName = parameters.get(0);
        title = parameters.get(1);
        description = parameters.get(2);
        priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class, INVALID_PRIORITY);
        size = ParsingHelpers.tryParseEnum(parameters.get(4), Size.class, INVALID_SIZE);
    }


}
