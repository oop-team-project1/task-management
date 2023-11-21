package taskmanagement.commands.creation;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.exceptions.ElementNotFoundException;
import taskmanagement.models.BoardImpl;
import taskmanagement.models.BoardImplTest;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.utils.TestHelpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateFeedbackInBoardTest {
    private Command command;
    private TaskManagementRepository taskManagementRepository;
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private static final String VALID_FEEDBACK_TITLE = TestHelpers.getString(11);
    private static final String INVALID_FEEDBACK_TITLE = TestHelpers.getString(9);
    private static final String VALID_FEEDBACK_DESCRIPTION = TestHelpers.getString(11);
    private static final String INVALID_FEEDBACK_DESCRIPTION = TestHelpers.getString(9);

    private static final String VALID_RATING = "2";
    private static final String INVALID_RATING = "0";
    @BeforeEach
    public void setUp() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateFeedbackInBoard(taskManagementRepository);
        Board board = new BoardImpl(BoardImplTest.VALID_BOARD_NAME);
        taskManagementRepository.addBoard(board);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestHelpers.getList( EXPECTED_NUMBER_OF_ARGUMENTS+ 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DifferentBoardNameLength() {
        List<String> params = List.of(BoardImplTest.INVALID_BOARD_NAME,
               VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_RATING);

        Assertions.assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DifferentTitleLength() {
        List<String> params = List.of(BoardImplTest.VALID_BOARD_NAME,
                INVALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_RATING);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DifferentDescriptionLength() {
        List<String> params = List.of(BoardImplTest.VALID_BOARD_NAME,
                VALID_FEEDBACK_TITLE,
                INVALID_FEEDBACK_DESCRIPTION,
                VALID_RATING);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DifferentRatingValue() {
        List<String> params = List.of(BoardImplTest.VALID_BOARD_NAME,
                VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                INVALID_RATING);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_CreateNewFeedbackInBoard_When_PassedValidInput() {
        List<String> params = List.of(BoardImplTest.VALID_BOARD_NAME,
                VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_RATING);

        command.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getTasks().size());
    }


}
