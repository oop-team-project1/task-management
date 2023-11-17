package taskmanagement.commands.listing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.TeamImpl;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import utils.TestHelpers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class ShowTeamMembersTest {



    private ShowTeamMembers showTeamMembers;
    private TaskManagementRepository repository;


    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        showTeamMembers = new ShowTeamMembers(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = TestHelpers.getList(ShowTeamMembers.EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamMembers.execute(params));

    }

    @Test
    public void should_ShowTeamMembers_When_ArgumentsAreValid(){
        Team team = new TeamImpl("gorillas");
        repository.addTeam(team);
        List<String> params = new ArrayList<>();
        params.add("gorillas");

        Assertions.assertDoesNotThrow(() -> showTeamMembers.execute(params));
    }

}