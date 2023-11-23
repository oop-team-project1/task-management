package taskmanagement.commands.listing.sorting;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.contracts.Story;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortStoriesByTitle implements Command {
    public static final String STORIES_SORT_TITLE_ERR_MSG = "There are no registered stories.";
    List<Story> stories;

    public SortStoriesByTitle(TaskManagementRepository taskManagementRepository) {
        stories = taskManagementRepository.getStories();
    }

    @Override
    public String execute(List<String> parameters) {
        if(stories.isEmpty()){
            return STORIES_SORT_TITLE_ERR_MSG;
        }
        return sortAllStoriesByTitle();
    }

    private String sortAllStoriesByTitle() {
        String sortedTitles = stories
                .stream()
                .sorted(Comparator.comparing(Story::getTitle))
                .map(Story::getTitle)
                .collect(Collectors.joining(", "));
        return sortedTitles;
    }
}
