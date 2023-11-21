package taskmanagement.commands.listing;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class ShowFeedbackComments implements Command
{
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementRepository taskManagementRepository;

    public ShowFeedbackComments(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int feedbackId = ParsingHelpers.tryParseInteger(parameters.get(0), "feedback id");

        return ShowFeedbackComments(feedbackId);
    }

    private String ShowFeedbackComments(int feedbackId)
    {
        Feedback feedback = taskManagementRepository.findFeedbackById(feedbackId);

        return feedback.showComments();
    }
}
