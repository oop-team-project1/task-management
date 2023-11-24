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
import taskmanagement.utils.TestHelpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class AddPersonToTeamTest {
    public static final int VALID_NAME_SIZE = 5;
    public static int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new AddPersonToTeam(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestHelpers.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_AddPersonToTeam_When_ArgumentsAreValid() {
        //Arrange
        List<String> params = List.of(TestHelpers.getString(VALID_NAME_SIZE), TestHelpers.getString(VALID_NAME_SIZE));
        Team team = new TeamImpl(TestHelpers.getString(VALID_NAME_SIZE));
        Member member = new MemberImpl(TestHelpers.getString(VALID_NAME_SIZE));
        taskManagementRepository.addTeam(team);
        taskManagementRepository.addMember(member);
        //Act
        command.execute(params);
        //Assert
        Assertions.assertEquals(1, taskManagementRepository.getTeams().get(0).getMembers().size());
    }
}




