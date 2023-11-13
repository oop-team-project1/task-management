package taskmanagement.models.tasks.contracts;

import taskmanagement.models.contracts.Assignable;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.bug.Status;

public interface Bug {

    //TODO We want the Member not the String name of the assignee, right?

    public Status getStatus();


}
