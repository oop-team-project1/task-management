package taskmanagement.models.tasks;

import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.contracts.Prioritizable;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.BugStatus;
import taskmanagement.models.tasks.enums.bug.Severity;

public class BugImpl extends TaskImpl<BugStatus> implements Bug {

    //TODO why concat?
    public static final String PRIORITY_CHANGE_ERR = "Priority already set to %s." +
                                                     "If you still wish to change it, please provide a valid value";

    public static final String SEVERITY_CHANGE_ERR =
            "Severity already set to %s." +
            "If you still wish to change it, please provide a valid value";

    public static final String STATUS_CHANGE_ERR =
            "Status already set to %s." +
            "If you still wish to change it, please provide a valid value";

    private Member assignee;
    private BugStatus status;
    private Priority priority;
    private Severity severity;
    private boolean isAssigned = true;

    public BugImpl(int id, String title, String description, Member assignee, Priority priority, Severity severity) {
        super(id, title, description);
        this.assignee = assignee;
        this.priority = priority;
        this.severity = severity;
    }


    public BugImpl(int id, String title, String description, Member assignee, Priority priority, Severity severity, BugStatus status) {
        this(id, title, description, assignee, priority, severity);
        this.status = status;
    }

    // TODO return copy
    @Override
    public Member getAssignee() {
        return assignee;
    }

    @Override
    public void setAssignee(Member member) {
        if (isAssigned) {
            throw new IllegalArgumentException();
        }
        assignee = member;
        isAssigned = true;
    }

    @Override
    public void removeAssignee(Member member) {
        //TODO overwrite equals in member

        if (!assignee.equals(member)) {
            throw new IllegalArgumentException();
        }
        assignee = null;
        isAssigned = false;


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

    @Override
    public void changeStatus(BugStatus newStatus) {
        if (this.status == newStatus) {
            throw new IllegalArgumentException(
                    String.format(STATUS_CHANGE_ERR, severity));
        }
        logEvent(String.format("Status changed from %s to %s", this.status, newStatus));
        status = newStatus;

    }


    @Override
    public Severity getSeverity() {
        return severity;
    }

    @Override
    public void changeSeverity(Severity newSeverity) {
        if (this.severity == newSeverity) {
            throw new IllegalArgumentException(
                    String.format(SEVERITY_CHANGE_ERR, severity));
        }
        logEvent(String.format("Severity changed from %s to %s", this.severity, newSeverity));
        severity = newSeverity;

    }
}
