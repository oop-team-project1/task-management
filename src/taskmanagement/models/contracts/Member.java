package taskmanagement.models.contracts;

import taskmanagement.models.tasks.contracts.Printable;
import taskmanagement.models.tasks.contracts.Task;

import java.util.List;

public interface Member extends Printable, IdentifiableByName {

    List<Task> getTask();
    List<String> getActivityHistory();
    void addTask(Task task);
    String viewActivity();


}
