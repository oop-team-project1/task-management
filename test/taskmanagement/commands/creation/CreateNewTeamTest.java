package taskmanagement.commands.creation;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.TeamImpl;
import taskmanagement.utils.TestHelpers;

import java.util.List;


class CreateNewTeamTest {

    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateNewTeam(taskManagementRepository);
    }


    @Test
    void createNewTeam_Should_Create_A_NewTeam() {
        command.execute(List.of(TestHelpers.getString(TeamImpl.NAME_LEN_MIN)));
        Assertions.assertEquals(1, taskManagementRepository.getTeams().size());
    }


}