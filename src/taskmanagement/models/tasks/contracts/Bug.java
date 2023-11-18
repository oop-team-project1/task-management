package taskmanagement.models.tasks.contracts;

import taskmanagement.models.contracts.Assignable;
import taskmanagement.models.tasks.enums.bug.BugStatus;

public interface Bug extends Assignable, Prioritizable, Task, Severe, GenericStatus<BugStatus> {

    //TODO We want the Member obj not the String name of the assignee, right?

    public BugStatus getStatus();
}
