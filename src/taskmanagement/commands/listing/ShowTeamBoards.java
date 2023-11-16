package taskmanagement.commands.listing;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.ListingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ShowTeamBoards implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String BOARDS_ERR_MESSAGE = "There are no registered boards";
    private final TaskManagementRepository taskManagementRepository;

    public ShowTeamBoards(TaskManagementRepository taskManagementRepository){
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);

      return getBoardsInATeam(name);
    }

    private String getBoardsInATeam (String name) {
        Team team = taskManagementRepository.findTeamByName(name);

        StringBuilder result = new StringBuilder();
        for(Board board : team.getBoards()) {
            result.append(board).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
