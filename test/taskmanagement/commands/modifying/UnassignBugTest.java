package taskmanagement.commands.modifying;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.TeamImpl;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.models.tasks.BugImpl;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.utils.TestHelpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnassignBugTest {

    public static final int VALID_NAME_SIZE = 5;
    public static final int VALID_TITLE_SIZE = 10;
    public static final int VALID_DESCRIPTION_SIZE = 10;
    public static final int BUG_ID = 1;

    public static int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new UnassignBug(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestHelpers.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_UnassignBug_When_BugIsAssigned() {
        //Arrange
        List<String> params = List.of(String.valueOf(BUG_ID), TestHelpers.getString(VALID_NAME_SIZE));
        Team team = new TeamImpl(TestHelpers.getString(VALID_NAME_SIZE));
        Member memberAssigned = new MemberImpl(TestHelpers.getString(VALID_NAME_SIZE));
        taskManagementRepository.addTeam(team);
        taskManagementRepository.addMember(memberAssigned);
        Bug bug = new BugImpl(1, TestHelpers.getString(VALID_TITLE_SIZE), TestHelpers.getString(VALID_DESCRIPTION_SIZE), memberAssigned, Priority.LOW, Severity.CRITICAL, TestHelpers.getList(10));
        taskManagementRepository.addBug(bug);
        memberAssigned.addTask(bug);
        command.execute(params);
        //Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, bug::getAssignee);
    }
}