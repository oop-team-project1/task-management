package taskmanagement.commands.listing;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.TeamImpl;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.TestHelpers;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeamsTest
{
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new ShowAllTeams(taskManagementRepository);
    }

    @Test
    public void should_ShowAllTeams_When_ArgumentsAreValid(){
        Team team = new TeamImpl("gorillas");
        taskManagementRepository.addTeam(team);
        List<String> params = new ArrayList<>();
        params.add("gorillas");

        Assertions.assertDoesNotThrow(() -> command.execute(params));
    }
}
