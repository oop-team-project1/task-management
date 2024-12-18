package taskmanagement.commands.creation;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class CreateFeedbackInBoard implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private final TaskManagementRepository taskManagementRepository;
    private String boardName;

    private String title;
    private String description;
   private int rating;

    public CreateFeedbackInBoard(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }
    //input createfeedback <boardName> <feedback fields - title, description, rating>

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Board board = taskManagementRepository.findBoardByName(boardName);

        Feedback feedback =  taskManagementRepository.createNewFeedback(title, description,rating);
        board.addTask(feedback, "Feedback");

        return String.format(CommandsConstants.FEEDBACK_CREATED_MESSAGE, feedback.getId());
    }

    private void parseParameters(List<String> parameters) {
        boardName = parameters.get(0);
        title = parameters.get(1);
        description = parameters.get(2);
        rating = ParsingHelpers.tryParseInteger(parameters.get(3), "rating");
    }
}
