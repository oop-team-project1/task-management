package taskmanagement.models;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.TestHelpers;

import java.util.List;

public class MemberImplTest {
    public static final String VALID_MEMBER_NAME = TestHelpers.getString(TeamImpl.NAME_LEN_MIN+1);

    public static final String INVALID_MEMBER_NAME = TestHelpers.getString(TeamImpl.NAME_LEN_MAX + 1);


    @Test
    public void should_Create_Member_When_ValidValuesArePassed() {
       Member member = initializeMember();
        Assertions.assertEquals(VALID_MEMBER_NAME, member.getName());
    }

    @Test
    public void constructor_Should_ThrowException_When_NameLengthOutOfBounds(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new MemberImpl(INVALID_MEMBER_NAME));
    }

    @Test
    public void getTask_Should_ReturnCopyOfCollection() {
        Member member = initializeMember();
        List<Task> tasks = member.getTask();
        List<Task> sameReference = member.getTask();

        Assertions.assertNotSame(tasks, sameReference);

    }

    @Test
    public void getActivityHistory_Should_ReturnCopyOfCollection() {
        Member member = initializeMember();
        List<String> history = member.getActivityHistory();
        List<String> sameReference = member.getActivityHistory();

        Assertions.assertNotSame(history, sameReference);

    }

    @Test
    public void addTask_Should_AddTask_When_TaskIsValid(){
        Member member = initializeMember();
        Task taskToAdd = BoardImplTest.initializeTestFeedback();
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> member.addTask(taskToAdd)),
                () -> Assertions.assertEquals(1, member.getTask().size())
        );
    }

    public static Member initializeMember() {
        return new MemberImpl(VALID_MEMBER_NAME);
    }
}
