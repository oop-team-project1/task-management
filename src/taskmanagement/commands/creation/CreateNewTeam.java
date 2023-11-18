package taskmanagement.commands.creation;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class CreateNewTeam implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementRepository taskManagementRepository;
    private String name;

    public CreateNewTeam(TaskManagementRepository taskManagementRepository)
    {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters)
    {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Team createTeam = taskManagementRepository.createNewTeam(name);

        return String.format(CommandsConstants.TEAM_CREATED_MESSAGE, createTeam.getName());
    }

    private void parseParameters(List<String> parameters)
    {
        name = parameters.get(0);
    }
}
