package taskmanagement.commands.listing;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ShowTeamActivity implements Command
{
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ShowTeamActivity(TaskManagementRepository taskManagementRepository)
    {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters)
    {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        return showTeamActivity(name);
    }

    private String showTeamActivity (String name) {
        Team team = taskManagementRepository.findTeamByName(name);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < team.getMembers().size(); i++)
        {
            stringBuilder.append(team.getMembers().get(i).viewActivity()).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
