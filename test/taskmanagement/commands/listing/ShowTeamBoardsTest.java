package taskmanagement.commands.listing;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.BoardImplTest;
import taskmanagement.models.TeamImpl;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.TestHelpers;

import java.util.ArrayList;
import java.util.List;

public class ShowTeamBoardsTest {
    private TaskManagementRepository repository;
    private ShowTeamBoards showTeamBoards;



    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        //TODO: change JUnit and add BeforeEach
        repository = new TaskManagementRepositoryImpl();
        showTeamBoards = new ShowTeamBoards(repository);
        List<String> params = TestHelpers.getList(ShowTeamBoards.EXPECTED_NUMBER_OF_ARGUMENTS+1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamBoards.execute(params));
    }

    @Test
    public void should_ShowTeamBoards_When_ArgumentsAreValid(){
        repository = new TaskManagementRepositoryImpl();
        showTeamBoards = new ShowTeamBoards(repository);
        //TODO: not sure if this is ok
       Team team = new TeamImpl(TestHelpers.getString(TeamImpl.NAME_LEN_MIN+1));
       repository.addTeam(team);
       List<String> params = new ArrayList<>();
       params.add(TestHelpers.getString(TeamImpl.NAME_LEN_MIN+1));

        Assertions.assertDoesNotThrow(() -> showTeamBoards.execute(params));
    }
}
