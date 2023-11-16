package taskmanagement.commands.changing;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.story.StoryStatus;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ChangeStoryStatus implements Command
{
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String ERROR_MESSAGE = "Invalid status type!";
    private final TaskManagementRepository taskManagementRepository;

    private int storyId;
    private StoryStatus status;

    public ChangeStoryStatus(TaskManagementRepository taskManagementRepository)
    {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters)
    {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Story story = taskManagementRepository.findStoryById(storyId);
        story.changeStatus(status);

        return String.format(CommandsConstants.STATUS_CHANGED_MESSAGE, story.getId());
    }

    private void parseParameters(List<String> parameters)
    {
        storyId = ParsingHelpers.tryParseInteger(parameters.get(0), "story id");
        status = ParsingHelpers.tryParseEnum(parameters.get(1), status.getDeclaringClass(), ERROR_MESSAGE);
    }
}
