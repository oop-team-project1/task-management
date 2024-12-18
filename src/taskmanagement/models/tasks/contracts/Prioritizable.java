package taskmanagement.models.tasks.contracts;

import taskmanagement.models.tasks.enums.Priority;

public interface Prioritizable {
    public Priority getPriority();
    public void changePriority(Priority newPriority);

}
