package taskmanagement.models.tasks.contracts;

import taskmanagement.models.tasks.enums.Priority;

public interface Prioritizable {
    Priority getPriority();
    void changePriority(Priority newPriority);

}
