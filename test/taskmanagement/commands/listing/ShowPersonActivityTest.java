package taskmanagement.commands.listing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.MemberImplTest;
import taskmanagement.models.contracts.Member;
import taskmanagement.utils.TestHelpers;


import java.util.List;

public class ShowPersonActivityTest {

    private TaskManagementRepository repository;
    private ShowPersonActivity showPersonActivity;

    @BeforeEach
    public void setUp() {
        repository = new TaskManagementRepositoryImpl();
        showPersonActivity = new ShowPersonActivity(repository);

    }
    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = TestHelpers.getList(ShowTeamBoards.EXPECTED_NUMBER_OF_ARGUMENTS+1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> showPersonActivity.execute(params));
    }

    @Test
    public void should_ShowPersonActivity_When_ArgumentsAreValid(){
        Member member = repository.createNewPerson(MemberImplTest.VALID_MEMBER_NAME);
        showPersonActivity.execute(List.of(member.getName()));

        Assertions.assertEquals(1, repository.getMembers().size());
    }
}
