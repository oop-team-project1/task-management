package taskmanagement.models;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.tasks.FeedbackImpl;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.TestHelpers;

import java.util.List;

public class BoardImplTest {
    public static final String VALID_BOARD_NAME = TestHelpers.getString(BoardImpl.NAME_MIN_LENGTH+1);
    public static final String INVALID_BOARD_NAME = TestHelpers.getString(BoardImpl.NAME_MAX_LENGTH+1);

    public static final int MIN_TITLE_LENGTH = 10;
   // private final int MAX_TITLE_LENGTH = 100;

   // public static final String VALID_NAME_FOR_FEEDBACK = TestHelpers.getString(MIN_TITLE_LENGTH + 1)

    @Test
    public void constructor_Should_ThrowException_When_NameLengthOutOfBounds(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BoardImpl(INVALID_BOARD_NAME));
    }

    @Test
    public void should_Create_Board_When_ValidValuesArePassed() {
        Board board = initializeBoard();

        Assertions.assertEquals(VALID_BOARD_NAME, board.getName());
    }

    @Test
    public void getTask_Should_ReturnCopyOfCollection() {
        Board board = initializeBoard();
        List<Task> tasks = board.getTask();
        List<Task> sameReference = board.getTask();

        Assertions.assertNotSame(tasks, sameReference);

    }

    @Test
    public void getActivityHistory_Should_ReturnCopyOfCollection() {
        Board board = initializeBoard();
        List<String> history = board.getActivityHistory();
        List<String> sameReference = board.getActivityHistory();

        Assertions.assertNotSame(history, sameReference);

    }

    @Test
    public void addTask_Should_AddTask_When_TaskIsValid(){
        Board board = initializeBoard();
        Task taskToAdd = initializeTestFeedback();
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> board.addTask(taskToAdd)),
                () -> Assertions.assertEquals(1, board.getTask().size())
        );
    }


    public static Board initializeBoard() {
        return new BoardImpl(VALID_BOARD_NAME);
    }
    public static Feedback initializeTestFeedback() {
        return new FeedbackImpl(1,
                TestHelpers.getString(MIN_TITLE_LENGTH + 1),
                TestHelpers.getString(MIN_TITLE_LENGTH+1),
                5);
    }
}
