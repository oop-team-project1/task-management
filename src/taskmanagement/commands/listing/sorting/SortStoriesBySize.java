package taskmanagement.commands.listing.sorting;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Story;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortStoriesBySize implements Command {
    private List<Story> stories;

    public SortStoriesBySize(TaskManagementRepository taskManagementRepository) {
        stories = taskManagementRepository.getStories();
    }

    @Override
    public String execute(List<String> parameters) {
        if(stories.isEmpty()){
            return SortStoriesByTitle.STORIES_SORT_TITLE_ERR_MSG;
        }
        return sortAllStoriesBySize();
    }

    private String sortAllStoriesBySize() {
        String sortedPriorities = stories
                .stream()
                .sorted(Comparator.comparing(Story::getSize))
                .map(Story::getSize)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return sortedPriorities;
    }
}
