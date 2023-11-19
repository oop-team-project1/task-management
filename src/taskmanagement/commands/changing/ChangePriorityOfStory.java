package taskmanagement.commands.changing;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ChangePriorityOfStory implements Command
{
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String ERROR_MESSAGE = "Invalid priority type!";
    private final TaskManagementRepository taskManagementRepository;

    private int storyId;
    private Priority priority;

    public ChangePriorityOfStory(TaskManagementRepository taskManagementRepository)
    {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters)
    {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Story story = taskManagementRepository.findStoryById(storyId);
        story.changePriority(priority);

        return String.format(CommandsConstants.STORY_PRIORITY_CHANGED_MESSAGE, story.getId());
    }

    private void parseParameters(List<String> parameters)
    {
        storyId = ParsingHelpers.tryParseInteger(parameters.get(0), "story id");
        priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class, ERROR_MESSAGE);
    }
}
