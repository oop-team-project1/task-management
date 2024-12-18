package taskmanagement.commands.changing;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ChangeFeedbackRating implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final TaskManagementRepository taskManagementRepository;
    private int feedbackId;
    private int rating;

    public ChangeFeedbackRating(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }
    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Feedback feedback = taskManagementRepository.findFeedbackById(feedbackId);
        feedback.changeRating(rating);

        return String.format(CommandsConstants.RATING_CHANGED_MESSAGE, feedback.getId());
    }

    private void parseParameters(List<String> parameters) {
        feedbackId = ParsingHelpers.tryParseInteger(parameters.get(0), "feedback id");
        rating = ParsingHelpers.tryParseInteger(parameters.get(1), "feedback rating");
    }
}
