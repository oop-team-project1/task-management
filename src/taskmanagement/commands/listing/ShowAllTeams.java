package taskmanagement.commands.listing;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.ListingHelpers;

import java.util.List;

public class ShowAllTeams implements Command
{
    public static final String TEAM_ERROR_MESSAGE = "There are no registered teams!";
    private final List<Team> teams;

    public ShowAllTeams(TaskManagementRepository taskManagementRepository)
    {
        teams = taskManagementRepository.getTeams();
    }

    @Override
    public String execute(List<String> parameters)
    {
        if (teams.isEmpty())
        {
            return TEAM_ERROR_MESSAGE;
        }

        return ListingHelpers.elementsToString(teams);
    }
}
