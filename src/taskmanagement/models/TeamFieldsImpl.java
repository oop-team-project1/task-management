package taskmanagement.models;

import taskmanagement.models.contracts.TeamFields;
import taskmanagement.models.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public abstract class TeamFieldsImpl implements TeamFields
{
    private String name;
    private List<Task> tasks;
    private List<String> activityHistory;

    protected TeamFieldsImpl(String name)
    {
        setName(name);

        tasks = new ArrayList<>(tasks);
        activityHistory = new ArrayList<>(activityHistory);
    }

    protected void setName(String name)
    {
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

    //abstract method?
}
