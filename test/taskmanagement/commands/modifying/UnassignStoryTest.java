package taskmanagement.commands.modifying;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.listing.ShowTeamMembers;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.utils.TestHelpers;

import java.util.List;


class UnassignStoryTest {

    private UnassignStory unassignStory;
    private TaskManagementRepository repository;

    @BeforeEach
    public void before() {
        repository = new TaskManagementRepositoryImpl();
        unassignStory = new UnassignStory(repository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestHelpers.getList(ShowTeamMembers.EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> unassignStory.execute(params));

    }

    @Test
    public void should_AddStory_To_MembersTasks() {
        //TODO
    }

    @Test
    public void should_AddAssignee_To_Story() {
        //TODO
    }


}