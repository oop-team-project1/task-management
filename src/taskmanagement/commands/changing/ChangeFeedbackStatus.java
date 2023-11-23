package taskmanagement.commands.changing;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.enums.feedback.FeedbackStatus;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ChangeFeedbackStatus implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String ERROR_MESSAGE = "Invalid status type!";
    private final TaskManagementRepository taskManagementRepository;

    private int feedbackId;
    private FeedbackStatus status;

    public ChangeFeedbackStatus(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Feedback feedback = taskManagementRepository.findFeedbackById(feedbackId);
        String prevStatus = feedback.getStatus().toString();
        feedback.changeStatus(status);
        String newStatus = feedback.getStatus().toString();

        return String.format(CommandsConstants.FEEDBACK_STATUS_CHANGED_MESSAGE, feedback.getId(), prevStatus,newStatus);
    }

    private void parseParameters(List<String> parameters) {
        feedbackId = ParsingHelpers.tryParseInteger(parameters.get(0), "feedback id");
        status = ParsingHelpers.tryParseFeedbackStatus(parameters.get(1));
    }

}
