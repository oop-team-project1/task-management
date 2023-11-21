package taskmanagement.commands.listing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.BoardImpl;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.tasks.FeedbackImpl;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.utils.TestHelpers;

import java.util.ArrayList;
import java.util.List;

public class ShowFeedbackCommentsTest
{
    private static final String VALID_FEEDBACK_TITLE = TestHelpers.getString(10);
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp()
    {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ShowFeedbackComments(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = TestHelpers.getList(ShowFeedbackComments.EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ShowFeedbackComments_When_ArgumentsAreValid(){
        Feedback feedback = new FeedbackImpl(1, VALID_FEEDBACK_TITLE, VALID_FEEDBACK_TITLE, 5);
        taskManagementRepository.addFeedback(feedback);
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(1));

        Assertions.assertDoesNotThrow(() -> command.execute(params));
    }
}
