package taskmanagement.commands.listing;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.TaskImpl;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.ValidationHelper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllTasks implements Command
{
    public static final String TASK_ERROR_MESSAGE = "There are no registered tasks!";
    public static final String PARAMETERS_ERROR_MESSAGE = "Wrong parameters for task!";
    public static final String PARAMETERS_COUNT_ERROR_MESSAGE = "Wrong parameters count for task!";
    private static final double PARAMETERS_COUNT_MIN = 1;
    private static final double PARAMETERS_COUNT_MAX = 2;

    private final List<Task> tasks;

    public ListAllTasks(TaskManagementRepository taskManagementRepository)
    {
        tasks = taskManagementRepository.getTasks();
    }

    // parameters: <filter> + <title> / <sort>
    @Override
    public String execute(List<String> parameters)
    {
        if (tasks.isEmpty())
        {
            return TASK_ERROR_MESSAGE;
        }
        ValidationHelper.validateValueEitherOfTwoNumbers(parameters.size(),PARAMETERS_COUNT_MIN,PARAMETERS_COUNT_MAX,
                PARAMETERS_COUNT_ERROR_MESSAGE);

        if (parameters.get(0).equals("filter"))
        {
            String title = parameters.get(1);
            ValidationHelper.validateStringLength(title, TaskImpl.MIN_TITLE_LENGTH, TaskImpl.MAX_TITLE_LENGTH,
                    TaskImpl.TITLE_LENGTH_ERROR);
            return filterAllTasksByTitle(title);
        }
        else if (parameters.get(0).equals("sort"))
        {
            return sortAllTasks();
        }
        else
        {
            return PARAMETERS_ERROR_MESSAGE;
        }
    }

    private String filterAllTasksByTitle(String title)
    {
        StringBuilder stringBuilder = new StringBuilder();
        tasks.stream().filter(task -> task.getTitle().equals(title)).forEach(task -> stringBuilder.append(task.print()));

        return stringBuilder.toString();
    }

    private String sortAllTasks()
    {
        String sortedTitles = tasks.stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .map(Task::getTitle)
                .collect(Collectors.joining(", "));

        return sortedTitles;
    }

}
