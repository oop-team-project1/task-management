package taskmanagement.commands.changing;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ChangeSizeOfStory implements Command
{
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String ERROR_MESSAGE = "Invalid size type!";
    private final TaskManagementRepository taskManagementRepository;

    private int storyId;
    private Size size;

    public ChangeSizeOfStory(TaskManagementRepository taskManagementRepository)
    {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters)
    {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Story story = taskManagementRepository.findStoryById(storyId);
        story.changeSize(size);

        return String.format(CommandsConstants.SIZE_CHANGED_MESSAGE, story.getId());
    }

    private void parseParameters(List<String> parameters)
    {
        storyId = ParsingHelpers.tryParseInteger(parameters.get(0), "story id");
        size = ParsingHelpers.tryParseEnum(parameters.get(1), Size.class, ERROR_MESSAGE);
    }
}
