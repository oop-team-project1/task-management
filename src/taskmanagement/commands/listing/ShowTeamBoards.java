package taskmanagement.commands.listing;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Board;
import taskmanagement.utils.ListingHelpers;

import java.util.List;

public class ShowTeamBoards implements Command {
    public static final String BOARDS_ERR_MESSAGE = "There are no registered boards";
    private final List<Board> boards;

    public ShowTeamBoards(TaskManagementRepository taskManagementRepository){
        boards = taskManagementRepository.getBoards();
    }

    @Override
    public String execute(List<String> parameters) {
      if(boards.isEmpty()) {
          return BOARDS_ERR_MESSAGE;
      }

      return ListingHelpers.elementsToString(boards);
    }
}
