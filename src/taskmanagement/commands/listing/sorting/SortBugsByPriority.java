package taskmanagement.commands.listing.sorting;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.contracts.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortBugsByPriority implements Command {
    private List<Bug> bugs;
    public SortBugsByPriority(TaskManagementRepository taskManagementRepository) {
        taskManagementRepository.getBugs();
    }

    @Override
    public String execute(List<String> parameters) {
        if(bugs.isEmpty()) {
            return SortBugsByTitle.BUG_SORT_TITLE_ERR_MSG;
        }
        return sortAllBugsByPriority();
    }

    private String sortAllBugsByPriority() {
        String sortedPriorities = bugs
                .stream()
                .sorted(Comparator.comparing(Bug::getPriority))
                .map(Bug::getPriority)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return sortedPriorities;
    }
}
