package taskmanagement.commands.listing.sorting;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Bug;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortBugsByTitle implements Command {
    public static final String BUG_SORT_TITLE_ERR_MSG = "There are no registered bugs!";
    private List<Bug> bugs;

    public SortBugsByTitle(TaskManagementRepository taskManagementRepository) {
        bugs = taskManagementRepository.getBugs();
    }

    @Override
    public String execute(List<String> parameters) {
        if(bugs.isEmpty()) {
            return BUG_SORT_TITLE_ERR_MSG;
        }
        return sortAllBugsByTitle();
    }

    private String sortAllBugsByTitle() {
        String sortedTitles = bugs
                .stream()
                .sorted(Comparator.comparing(Bug::getTitle))
                .map(Bug::getTitle)
                .collect(Collectors.joining(", "));
        return sortedTitles;
    }
}
