package taskmanagement.models.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.MemberImplTest;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.BugStatus;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.utils.TaskBaseConstants;


import java.util.List;


class BugImplTest {

    public static final List<String> stepsToReproduce = List.of(
            "1.Load the program.", "2.Press the big red button", "3.Wait for the launch", "4.Get an error instead.");

    @Test
    void bugImpl_Should_ImplementBugInterface() {
        BugImpl bug = initializeTestBug();
        Assertions.assertTrue(bug instanceof Bug);
    }


    @Test
    void getAssignee_Should_Return_Copy_Of_Assignee() {
        BugImpl bug = initializeTestBug();
        bug.getAssignee().addTask(initializeTestBug());
        Assertions.assertEquals(0, bug.getAssignee().getTask().size());
    }

    @Test
    void setAssignee_Should_Throw_When_TaskIsAssignedAlready() {
        Bug bug = initializeTestBug();
        Member member = new MemberImpl("xxxxx");
        Assertions.assertThrows(IllegalArgumentException.class, () -> bug.setAssignee(member));

    }

    @Test
    void removeAssignee_Should_Throw_When_InputDoesNotMatchAssignedMember() {
        Bug bug = initializeTestBug();
        Member member = new MemberImpl("xxxxx");
        Assertions.assertThrows(IllegalArgumentException.class, () -> bug.removeAssignee(member));
    }

    @Test
    void changePriority_Should_Throw_When_PriorityAlreadySetToGivenInput() {
        Bug bug = initializeTestBug();
        Assertions.assertThrows(IllegalArgumentException.class, () -> bug.changePriority(TaskBaseConstants.VALID_PRIORITY));

    }

    @Test
    void changeStatus_Should_Throw_When_StatusAlreadySetToGivenInput() {
        Bug bug = initializeTestBug();
        Assertions.assertThrows(IllegalArgumentException.class, () -> bug.changeStatus(BugStatus.ACTIVE));

    }

    @Test
    void changeSeverity_Should_Throw_When_SeverityAlreadySetToGivenInput() {
        Bug bug = initializeTestBug();
        Assertions.assertThrows(IllegalArgumentException.class, () -> bug.changeSeverity(TaskBaseConstants.VALID_SEVERITY));

    }

    @Test
    void changeSeverity_Should_LogEvent() {
        Bug bug = initializeTestBug();
        bug.changeSeverity(Severity.CRITICAL);
        Assertions.assertEquals(2, bug.getHistory().size());
    }

    @Test
    void changeStatus_Should_LogEvent() {
        Bug bug = initializeTestBug();
        bug.changeStatus(BugStatus.DONE);
        Assertions.assertEquals(2, bug.getHistory().size());
    }

    @Test
    void changeStatus_Should_ChangeStatus() {
        Bug bug = initializeTestBug();
        bug.changeStatus(BugStatus.DONE);
        Assertions.assertEquals(BugStatus.DONE, bug.getStatus());
    }

    @Test
    void changePriority_Should_LogEvent() {
        Bug bug = initializeTestBug();
        bug.changePriority(Priority.MEDIUM);
        Assertions.assertEquals(2, bug.getHistory().size());
    }


    public static BugImpl initializeTestBug() {
        Member member = MemberImplTest.initializeMember();

        return new BugImpl(TaskBaseConstants.TASK_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                member,
                TaskBaseConstants.VALID_PRIORITY,
                TaskBaseConstants.VALID_SEVERITY,
                stepsToReproduce);


    }


}