package taskmanagement.commands.listing.sorting;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Feedback;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortFeedbacksByRating implements Command {
    private List<Feedback> feedbacks;
    public SortFeedbacksByRating(TaskManagementRepository taskManagementRepository) {
        feedbacks = taskManagementRepository.getFeedbacks();
    }
    @Override
    public String execute(List<String> parameters) {
        if(feedbacks.isEmpty()){
            return SortFeedbacksByTitle.FEEDBACKS_SORT_TITLE_ERR_MSG;
        }
        return sortAllFeedbacksByRating();
    }

    private String sortAllFeedbacksByRating() {
        String sortedRatings = feedbacks
                .stream()
                .sorted(Comparator.comparing(Feedback::getRating))
                .map(Feedback::getRating)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return sortedRatings;
    }
}
