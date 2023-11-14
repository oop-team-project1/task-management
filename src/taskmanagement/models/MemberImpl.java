package taskmanagement.models;

import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.TaskImpl;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl implements Member {

    public static final String NAME_LENGTH_ERR = "Member name must be between %s and %s characters long!";
    public static final int NAME_MAX_LENGTH = 10;
    public static final String ADD_TASK_TO_LIST = "Task  %s added to member %s.";
    public static final String HISTORY_MESSAGE = "Activity History on Member %s";
    private String name;
    private List<Task> tasks;
    private List<String> activityHistory;

    public MemberImpl(String name) {
        setName(name);
        tasks = new ArrayList<>();
        activityHistory = new ArrayList<>();
    }

    private void setName(String name) {
        ValidationHelper.validateStringLength(name,
                                            TeamImpl.NAME_LEN_MIN,
                NAME_MAX_LENGTH,
                String.format(NAME_LENGTH_ERR,
                        TeamImpl.NAME_LEN_MIN,
                        NAME_MAX_LENGTH));
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
        String message = String.format(ADD_TASK_TO_LIST, task.getTitle(),
                getName());
        activityHistory.add(message);

    }

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
