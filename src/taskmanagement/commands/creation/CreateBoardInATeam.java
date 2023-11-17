package taskmanagement.commands.creation;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class CreateBoardInATeam implements Command {

    private static final String BOARD_ADDED_TO_TEAM = "Board %s added to team %s!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;

    public CreateBoardInATeam(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }
   // input: createboard <teamName> <boardName>

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamToAdd = parameters.get(0);
        String boardToAdd = parameters.get(1);
        return addBoardToATeam(teamToAdd, boardToAdd);
    }

    private String addBoardToATeam(String teamToAdd, String boardToAdd) {
        Team team = taskManagementRepository.findTeamByName(teamToAdd);
        Board board = taskManagementRepository.createNewBoard(boardToAdd);

        team.addBoard(board);

        return String.format(BOARD_ADDED_TO_TEAM, boardToAdd, teamToAdd);
    }

}
