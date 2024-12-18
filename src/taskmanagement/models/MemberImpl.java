package taskmanagement.models;

import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.ValidationHelper;

import java.util.ArrayList;
import java.util.List;


public class MemberImpl implements Member {
    public static final String NAME_LENGTH_ERR = "Member name must be between %s and %s characters long!";
    public static final String ADD_TASK_TO_LIST = "Task %s added to member %s.";
    public static final String HISTORY_MESSAGE = "Activity History on member %s:";
    public static final String NO_SUCH_TASK_ERR = "Member %s has not been assigned task with id %d!";
    private static final String REMOVE_TASK_FROM_LIST = "Task with id %d has been unassigned from member %s";
    private String name;
    private final List<Task> tasks;
    private final List<String> activityHistory;

    public MemberImpl(String name) {
        setName(name);
        tasks = new ArrayList<>();
        activityHistory = new ArrayList<>();
    }

    /**
     * Implementation of Copy Constructor to avoid importing 3rd party libraries and using clone() method.
     *
     * @param member member to be copied
     */
    public MemberImpl(Member member) {
        this.name = member.getName();
        this.tasks = member.getTask();
        this.activityHistory = member.getActivityHistory();
    }

    private void setName(String name) {
        ValidationHelper.validateStringLength(name,
                BoardImpl.NAME_MIN_LENGTH,
                BoardImpl.NAME_MAX_LENGTH,
                String.format(NAME_LENGTH_ERR,
                        BoardImpl.NAME_MIN_LENGTH,
                        BoardImpl.NAME_MAX_LENGTH));
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

    @Override
    public void removeTask(Task task) {
        if (!tasks.contains(task)) throw new IllegalArgumentException(String.format(
                NO_SUCH_TASK_ERR, this.name, task.getId()));
        tasks.remove(task);
        String message = String.format(REMOVE_TASK_FROM_LIST, task.getId(), name);
        activityHistory.add(message);
    }

    @Override
    public String viewActivity() {
        StringBuilder result = new StringBuilder(String.format(HISTORY_MESSAGE, getName()));
        result.append(System.lineSeparator());
        for (String activity : activityHistory) {
            result.append(activity).append(System.lineSeparator());
        }

        return result.toString();
    }

    @Override
    public String print() {
        StringBuilder result = new StringBuilder();
        result.append(getName());
        return result.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (object.getClass() != this.getClass()) {
            return false;
        }

        final Member other = (Member) object;

        return this.name.equals(other.getName());

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.name.hashCode();
        return hash;
    }
}
