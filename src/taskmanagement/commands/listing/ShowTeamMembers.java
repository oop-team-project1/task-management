package taskmanagement.commands.listing;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ShowTeamMembers implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String TEAM_EMPTY_ERR = "There are no members in this team!";
    private final TaskManagementRepository taskManagementRepository;

    public ShowTeamMembers(TaskManagementRepository taskManagementRepository){
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);

        return getMembersInATeam(name);
    }

    private String getMembersInATeam (String name) {
        Team team = taskManagementRepository.findTeamByName(name);

        StringBuilder result = new StringBuilder();
        for(Member member : team.getMembers()) {
            result.append(member).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}