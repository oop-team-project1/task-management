package taskmanagement.commands.listing.sorting;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Feedback;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortFeedbacksByTitle implements Command {
    public static final String FEEDBACKS_SORT_TITLE_ERR_MSG = "There are no registered feedbacks.";
    private List<Feedback> feedbacks;

    public SortFeedbacksByTitle(TaskManagementRepository taskManagementRepository) {
        feedbacks = taskManagementRepository.getFeedbacks();
    }
    @Override
    public String execute(List<String> parameters) {
        if(feedbacks.isEmpty()){
            return FEEDBACKS_SORT_TITLE_ERR_MSG;
        }
        return sortAllFeedbacksByTitle();
    }

    private String sortAllFeedbacksByTitle() {
        String sortedTitles = feedbacks
                .stream()
                .sorted(Comparator.comparing(Feedback::getTitle))
                .map(Feedback::getTitle)
                .collect(Collectors.joining(", "));
        return sortedTitles;
    }
}
