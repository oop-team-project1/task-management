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
    private static final int EXPECTED_NUMBER_OF_PARAMS = 7;
    private static final String VALID_BUG_TITLE = "xxxxxxxxxx";
    private static final String VALID_BUG_DESCRIPTION = "xxxxxxxxxx";
    private static final String VALID_BUG_ASSIGNEE_NAME = "xxxxx";
    private static final String VALID_BOARD_NAME = "xxxxx";
    private static final String INVALID_TEXT = "xxx";



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
        List<String> params = TestHelpers.getList(EXPECTED_NUMBER_OF_PARAMS + 1);

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
                String.valueOf(BugStatus.DONE));

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
                String.valueOf(BugStatus.DONE));

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
                String.valueOf(BugStatus.DONE));

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DifferentAssigneeNameLenght()
    {
        List<String> params = List.of(VALID_BOARD_NAME,
                VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                INVALID_TEXT,
                String.valueOf(Priority.LOW),
                String.valueOf(Severity.CRITICAL),
                String.valueOf(BugStatus.DONE));

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
                String.valueOf(BugStatus.DONE));

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
                String.valueOf(BugStatus.DONE));

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
                INVALID_TEXT);

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
                String.valueOf(Severity.CRITICAL));

        Board board = new BoardImpl(VALID_BOARD_NAME);
        Member member = new MemberImpl(VALID_BUG_ASSIGNEE_NAME);
        taskManagementRepository.addMember(member);
        taskManagementRepository.addBoard(board);


        command.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getTasks().size());
    }
}
