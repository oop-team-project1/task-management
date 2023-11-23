package taskmanagement.commands.listing.sorting;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Bug;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortBugsBySeverity implements Command {
    private List<Bug> bugs;

    public SortBugsBySeverity(TaskManagementRepository taskManagementRepository) {
        bugs = taskManagementRepository.getBugs();
    }
    @Override
    public String execute(List<String> parameters) {
        if(bugs.isEmpty()) {
            return SortBugsByTitle.BUG_SORT_TITLE_ERR_MSG;
        }
        return sortAllBugsBySeverity();
    }

    private String sortAllBugsBySeverity() {
        String sortedSeverity = bugs
                .stream()
                .sorted(Comparator.comparing(Bug::getSeverity))
                .map(Bug::getSeverity)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return sortedSeverity;
    }
}
