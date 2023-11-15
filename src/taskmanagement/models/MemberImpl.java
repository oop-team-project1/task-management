package taskmanagement.models;

import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.TaskImpl;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl implements Member {

    public static final String NAME_LENGTH_ERR = "Member name must be between %s and %s characters long!";
    private String name;
    private List<TaskImpl> taskImpls;
    private List<String> activityHistory;

    public MemberImpl(String name) {
        setName(name);
        taskImpls = new ArrayList<>();
        activityHistory = new ArrayList<>();
    }

    private void setName(String name)
    {
        ValidationHelper.validateStringLength(name,
                                            TeamImpl.NAME_LEN_MIN,
                                            TeamImpl.NAME_LEN_MAX,
                                            String.format(NAME_LENGTH_ERR,
                                                    TeamImpl.NAME_LEN_MIN,
                                                    TeamImpl.NAME_LEN_MAX));
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public List<Task> getTask()
    {
        return new ArrayList<>(taskImpls);
    }

    @Override
    public List<String> getActivityHistory()
    {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public void addTask(Task task)
    {

    }

    @Override
    public String viewActivity()
    {
        return null;
    }

    @Override
    public String print() {
        return null;
    }
}
