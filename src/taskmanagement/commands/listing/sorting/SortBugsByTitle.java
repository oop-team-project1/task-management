package taskmanagement.commands.listing.sorting;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.contracts.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortBugsByTitle implements Command {
    private List<Bug> bugs;

    public SortBugsByTitle(TaskManagementRepository taskManagementRepository) {
        bugs = taskManagementRepository.getBugs();
    }

    @Override
    public String execute(List<String> parameters) {
        if(bugs.isEmpty()) {
            return "There are no registered bugs!";
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
