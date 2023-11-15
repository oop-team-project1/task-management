package taskmanagement.models.contracts;

import taskmanagement.models.tasks.contracts.Task;

import java.util.List;

public interface Member {
    String getName();

    List<Task> getTask();
    List<String> getActivityHistory();
    void addTask(Task task);
    String viewActivity();
    String print();

}
