package taskmanagement.commands.listing;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Team;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.ListingHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllTasks implements Command
{
    private static final String TASK_ERROR_MESSAGE = "There are no registered tasks!";
    private static final String PARAMETERS_ERROR_MESSAGE = "Wrong parameters for task!";

    private final List<Task> tasks;

    public ListAllTasks(TaskManagementRepository taskManagementRepository)
    {
        tasks = taskManagementRepository.getTasks();
    }

    // parameters: <filter> <title> or <sort>
    @Override
    public String execute(List<String> parameters)
    {
        if (tasks.isEmpty())
        {
            return TASK_ERROR_MESSAGE;
        }

        if (parameters.get(0).equals("filter"))
        {
            String title = parameters.get(1);
            StringBuilder stringBuilder = new StringBuilder();
            tasks.stream().filter(task -> task.getTitle().equals(title)).forEach(task -> stringBuilder.append(task.getTitle()));

            return stringBuilder.toString();
        }
        else if (parameters.get(0).equals("sort"))
        {
            String sortedTitles = tasks.stream()
                    .sorted(Comparator.comparing(Task::getTitle))
                    .map(Task::getTitle)
                    .collect(Collectors.joining(", "));

            return sortedTitles;
        }
        else
        {
            return PARAMETERS_ERROR_MESSAGE;
        }
    }


}
