package taskmanagement.models;

import taskmanagement.models.contracts.Board;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String ADD_TASK_TO_LIST = "Task %s added to board %s";
    public static final String HISTORY_MESSAGE = "Activity History on Board %s";
    private String name;
    private List<Task> tasks;
    private List<String> activityHistory;

    public BoardImpl(String name) {
        setName(name);
        tasks = new ArrayList<>();
        activityHistory = new ArrayList<>();
    }

    private void setName(String name) {
        ValidationHelper.validateStringLength(name,
                NAME_MIN_LENGTH,
                NAME_MAX_LENGTH,
                String.format("Name should be between %d and %d characters!", NAME_MIN_LENGTH, NAME_MAX_LENGTH ));
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Task> getTask() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<String> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        String message = String.format(ADD_TASK_TO_LIST, task.getTitle(), name);
        activityHistory.add(message);

    }

    //view activity on a board?

    @Override
    public String viewActivity() {
        StringBuilder result = new StringBuilder(String.format(HISTORY_MESSAGE, getName()));
        result.append(System.lineSeparator());
        for(String activity : activityHistory) {
            result.append(activity).append(System.lineSeparator());
        }

        return result.toString();
    }
}
