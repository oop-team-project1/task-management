package taskmanagement.commands.listing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.commands.creation.CreateNewPerson;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.BoardImpl;
import taskmanagement.models.TeamImpl;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.TestHelpers;

import java.util.ArrayList;
import java.util.List;

public class ShowBoardActivityTest
{
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp()
    {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ShowBoardActivity(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = TestHelpers.getList(ShowBoardActivity.EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ShowBoardActivity_When_ArgumentsAreValid(){
        Board board = new BoardImpl(TestHelpers.getString(5));
        taskManagementRepository.addBoard(board);
        List<String> params = new ArrayList<>();
        params.add(TestHelpers.getString(5));

        Assertions.assertDoesNotThrow(() -> command.execute(params));
    }
}
