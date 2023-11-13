package taskmanagement.models.contracts;

import taskmanagement.models.tasks.Task;

import java.util.List;

public interface TeamFields
{
    String getName();

    List<Task> getTask();
    List<String> getActivityHistory();
}
