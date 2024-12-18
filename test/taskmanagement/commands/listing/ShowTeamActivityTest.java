package taskmanagement.commands.listing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.BoardImpl;
import taskmanagement.models.TeamImpl;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.TestHelpers;

import java.util.ArrayList;
import java.util.List;

public class ShowTeamActivityTest
{
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp()
    {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ShowTeamActivity(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = TestHelpers.getList(ShowTeamActivity.EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ShowTeamActivity_When_ArgumentsAreValid(){
        Team team = new TeamImpl(TestHelpers.getString(5));
        taskManagementRepository.addTeam(team);
        List<String> params = new ArrayList<>();
        params.add(TestHelpers.getString(5));

        Assertions.assertDoesNotThrow(() -> command.execute(params));
    }
}
