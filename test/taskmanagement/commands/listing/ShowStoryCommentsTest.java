package taskmanagement.commands.listing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.StoryImpl;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.utils.TestHelpers;

import java.util.ArrayList;
import java.util.List;

public class ShowStoryCommentsTest
{
    private static final String VALID_FEEDBACK_TITLE = TestHelpers.getString(10);
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp()
    {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ShowStoryComments(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = TestHelpers.getList(ShowStoryComments.EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ShowStoryComments_When_ArgumentsAreValid(){
        Story story = new StoryImpl(1, VALID_FEEDBACK_TITLE, VALID_FEEDBACK_TITLE, Priority.LOW, Size.MEDIUM);
        taskManagementRepository.addStory(story);
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(1));

        Assertions.assertDoesNotThrow(() -> command.execute(params));
    }
}
