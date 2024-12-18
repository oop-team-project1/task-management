package taskmanagement.models.tasks;

import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.contracts.Prioritizable;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.BugStatus;

public class BugImpl extends TaskImpl<BugStatus> implements Bug {
    public static final String PRIORITY_CHANGE_ERR = "Priority already set to %s. " +
                                                     "If you still wish to change it, " +
                                                     "please provide a valid value.";

    private final Member assignee;
    private BugStatus status;
    private Priority priority;

    public BugImpl(int id, String title, String description, Member assignee, Priority priority) {
        super(id, title, description);
        this.assignee = assignee;
        this.priority = priority;
    }


    public BugImpl(int id, String title, String description, Member assignee, Priority priority, BugStatus status) {
        this(id, title, description, assignee, priority);
        this.status = status;
    }

    // TODO return copy
    @Override
    public Member getAssignee() {
        return assignee;
    }

    @Override
    protected void setStatus(BugStatus status) {
        logEvent(String.format("Status changed from %s to %s", this.getStatus(), status));
        this.status = status;

    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public void changePriority(Priority newPriority) {
        if (this.priority == newPriority) {
            throw new IllegalArgumentException(
                    String.format(PRIORITY_CHANGE_ERR, priority));
        }
        logEvent(String.format("Priority changed from %s to %s", this.priority, newPriority));
        this.priority = newPriority;
    }


    @Override
    public BugStatus getStatus() {
        return status;
    }
}
