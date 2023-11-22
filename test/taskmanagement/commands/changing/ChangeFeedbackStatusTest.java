package taskmanagement.commands.changing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.commands.creation.CreateFeedbackInBoardTest;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.FeedbackImpl;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.enums.feedback.FeedbackStatus;
import taskmanagement.utils.TestHelpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeFeedbackStatusTest {
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ChangeFeedbackStatus(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestHelpers.getList(ChangeFeedbackStatus.EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
    @Test
    public void should_ChangeFeedbackStatus_When_ArgumentsAreValid() {
        List<String> params = List.of(String.valueOf(1), String.valueOf(FeedbackStatus.UNSCHEDULED));

        Feedback feedback = new FeedbackImpl(1,
                CreateFeedbackInBoardTest.VALID_FEEDBACK_TITLE,
                CreateFeedbackInBoardTest.VALID_FEEDBACK_DESCRIPTION,
                2);
        taskManagementRepository.addFeedback(feedback);

        taskManagementRepository.addTask(feedback);

        command.execute(params);

        Assertions.assertEquals(FeedbackStatus.UNSCHEDULED, feedback.getStatus());
    }

}
