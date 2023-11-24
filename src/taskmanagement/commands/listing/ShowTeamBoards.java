package taskmanagement.commands.listing;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ShowTeamBoards implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String BOARDS_ERR_MESSAGE = "There are no registered boards!";
    public static final String SHOW_BOARDS_MSG = "Boards in %s:";
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

        if(team.getBoards().isEmpty()) {
            return BOARDS_ERR_MESSAGE;
        }

        StringBuilder result = new StringBuilder();
        result.append(String.format(SHOW_BOARDS_MSG, team.getName())).append(System.lineSeparator());
        for(Board board : team.getBoards()) {
            result.append(board.getName()).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
