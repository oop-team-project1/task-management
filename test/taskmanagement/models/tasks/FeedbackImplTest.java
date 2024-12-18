package taskmanagement.models.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.enums.feedback.FeedbackStatus;
import taskmanagement.utils.TaskBaseConstants;



class FeedbackImplTest {
    public static final int VALID_RATING = 5;
    public static final int INVALID_RATING = 10;


    @Test
    void storyImpl_Should_Implement_StoryInterface(){
        FeedbackImpl feedback = initializeTestFeedback();
        Assertions.assertTrue(feedback instanceof Feedback);
    }

    @Test
    void constructor_Should_Throw_When_RatingIsInvalid() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new FeedbackImpl(TaskBaseConstants.TASK_ID,
                        TaskBaseConstants.VALID_TITLE,
                        TaskBaseConstants.VALID_DESCRIPTION,
                        INVALID_RATING));
    }

    @Test
    void changeStatus_Should_Throw_When_SizeAlreadySetToGivenInput() {
        Feedback feedback = initializeTestFeedback();
        Assertions.assertThrows(IllegalArgumentException.class, () -> feedback.changeStatus(FeedbackStatus.NEW));
    }
    @Test
    void changeRating_Should_Throw_When_RatingIsInvalid() {
        Feedback feedback = initializeTestFeedback();
        Assertions.assertThrows(IllegalArgumentException.class, () -> feedback.changeRating(INVALID_RATING));
    }


    public static FeedbackImpl initializeTestFeedback() {
        return new FeedbackImpl(TaskBaseConstants.TASK_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                VALID_RATING);
    }
}