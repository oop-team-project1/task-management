package taskmanagement.commands.creation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.BoardImplTest;
import taskmanagement.models.TeamImpl;
import taskmanagement.utils.TestHelpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateBoardInATeamTest {

    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateNewPerson(taskManagementRepository);
    }
    //TODO opravi celiq test

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestHelpers.getList(CreateBoardInATeam.EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_CreateBoardInATeam_When_PassedValidInput() {
        List<String> params = List.of(
                TestHelpers.getString(TeamImpl.NAME_LEN_MIN+1),
                BoardImplTest.VALID_BOARD_NAME
        );

        command.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getBoards().size());
    }
}
