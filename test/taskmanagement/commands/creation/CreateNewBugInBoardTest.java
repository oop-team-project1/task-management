package taskmanagement.commands.creation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.BoardImpl;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.BugStatus;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.utils.TestHelpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateNewBugInBoardTest
{
    public static final String VALID_BUG_TITLE = TestHelpers.getString(10);
    public static final String VALID_BUG_DESCRIPTION = TestHelpers.getString(10);
    public static final String VALID_BUG_ASSIGNEE_NAME = TestHelpers.getString(5);
    public static final String VALID_BOARD_NAME = TestHelpers.getString(5);
    private static final String INVALID_TEXT = TestHelpers.getString(3);


    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp()
    {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateNewBugInBoard(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected()
    {
        List<String> params = TestHelpers.getList(CreateNewBugInBoard.MAX_NUMBER_OF_ARGUMENTS + 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DifferentBoardNameLenght()
    {
        List<String> params = List.of(INVALID_TEXT,
                VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_ASSIGNEE_NAME,
                String.valueOf(Priority.LOW),
                String.valueOf(Severity.CRITICAL),
                String.valueOf(BugStatus.DONE),
                TestHelpers.getString(5));

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DifferentTitleLenght()
    {
        List<String> params = List.of(VALID_BOARD_NAME,
                INVALID_TEXT,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_ASSIGNEE_NAME,
                String.valueOf(Priority.LOW),
                String.valueOf(Severity.CRITICAL),
                String.valueOf(BugStatus.DONE),
                TestHelpers.getString(5));

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DifferentDescriptionLenght()
    {
        List<String> params = List.of(VALID_BOARD_NAME,
                VALID_BUG_TITLE,
                INVALID_TEXT,
                VALID_BUG_ASSIGNEE_NAME,
                String.valueOf(Priority.LOW),
                String.valueOf(Severity.CRITICAL),
                String.valueOf(BugStatus.DONE),
                TestHelpers.getString(5));

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DifferentAssigneeNameLength()
    {
        List<String> params = List.of(VALID_BOARD_NAME,
                VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                INVALID_TEXT,
                String.valueOf(Priority.LOW),
                String.valueOf(Severity.CRITICAL),
                String.valueOf(BugStatus.DONE),
                TestHelpers.getString(5));

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidPriority()
    {
        List<String> params = List.of(VALID_BOARD_NAME,
                VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_ASSIGNEE_NAME,
                INVALID_TEXT,
                String.valueOf(Severity.CRITICAL),
                String.valueOf(BugStatus.DONE),
                TestHelpers.getString(5));

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidSeverity()
    {
        List<String> params = List.of(VALID_BOARD_NAME,
                VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_ASSIGNEE_NAME,
                String.valueOf(Priority.LOW),
                INVALID_TEXT,
                String.valueOf(BugStatus.DONE),
                TestHelpers.getString(5));

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidBugStatus()
    {
        List<String> params = List.of(VALID_BOARD_NAME,
                VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_ASSIGNEE_NAME,
                String.valueOf(Priority.LOW),
                String.valueOf(Severity.CRITICAL),
                INVALID_TEXT,
                TestHelpers.getString(5));

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_CreateNewBugInBoard_When_PassedValidInput()
    {
        List<String> params = List.of(VALID_BOARD_NAME,
                VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_ASSIGNEE_NAME,
                String.valueOf(Priority.LOW),
                String.valueOf(Severity.CRITICAL),
                TestHelpers.getString(5));

        Board board = new BoardImpl(VALID_BOARD_NAME);
        Member member = new MemberImpl(VALID_BUG_ASSIGNEE_NAME);
        taskManagementRepository.addMember(member);
        taskManagementRepository.addBoard(board);

        command.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getTasks().size());
    }
}
