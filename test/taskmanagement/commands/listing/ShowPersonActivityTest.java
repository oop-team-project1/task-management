package taskmanagement.commands.listing;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.MemberImplTest;
import taskmanagement.models.TeamImpl;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.TestHelpers;

import java.util.ArrayList;
import java.util.List;

public class ShowPersonActivityTest {

    private TaskManagementRepository repository;
    private ShowPersonActivity showPersonActivity;

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        //TODO: change JUnit and add BeforeEach
        repository = new TaskManagementRepositoryImpl();
        showPersonActivity = new ShowPersonActivity(repository);
        List<String> params = TestHelpers.getList(ShowTeamBoards.EXPECTED_NUMBER_OF_ARGUMENTS+1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> showPersonActivity.execute(params));
    }

    @Test
    public void should_ShowPersonActivity_When_ArgumentsAreValid(){
        repository = new TaskManagementRepositoryImpl();
        showPersonActivity = new ShowPersonActivity(repository);
        //TODO: not sure if this is ok
        Member member = repository.createNewPerson(MemberImplTest.VALID_MEMBER_NAME);
        showPersonActivity.execute(List.of(member.getName()));
        Assertions.assertEquals(1, repository.getMembers().size());
    }
}
