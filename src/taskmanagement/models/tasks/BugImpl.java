package taskmanagement.models.tasks;

import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.BugStatus;
import taskmanagement.models.tasks.enums.bug.Severity;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

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
    private List<String> stepsToReproduce;
    private boolean isAssigned = true;

    public BugImpl(int id, String title, String description, Member assignee, Priority priority, Severity severity, List<String> stepsToReproduce) {
        super(id, title, description);
        this.assignee = assignee;
        this.priority = priority;
        this.severity = severity;
        this.status = BugStatus.ACTIVE;
        setStepsToReproduce(stepsToReproduce);

    }

    public BugImpl(int id, String title, String description, Member assignee, Priority priority, Severity severity, BugStatus status,List<String> stepsToReproduce ) {
        this(id, title, description, assignee, priority, severity,stepsToReproduce);
        this.status = status;
    }

    private void setStepsToReproduce(List<String> stepsToReproduce) {
        if(stepsToReproduce.isEmpty()) throw new IllegalArgumentException("List of steps to reproduce the bug can't be empty!");
        this.stepsToReproduce = new ArrayList<>(stepsToReproduce);
    }


    // TODO return copy
    @Override
    public Member getAssignee() {
        return assignee;
    }

    private void setStatus(BugStatus status) {
        logEvent(String.format("Status changed from %s to %s", this.getStatus(), status));
        this.status = status;

    }

    @Override
    public void setAssignee(Member member)
    {
        if (isAssigned) {
            throw new IllegalArgumentException();
        }
        assignee = member;
        isAssigned = true;
    }

    @Override
    public void removeAssignee(Member member)
    {
        //TODO overwrite equals in member

        if (!assignee.equals(member)) {
            throw new IllegalArgumentException();
        }
        assignee = null;
        isAssigned = false;
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
                    String.format(STATUS_CHANGE_ERR, status));
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
                    String.format(SEVERITY_CHANGE_ERR,severity));
        }
        logEvent(String.format("Severity changed from %s to %s", this.severity, newSeverity));
        severity = newSeverity;

    }

    @Override
    public void revertStatus() {
        if(status==BugStatus.ACTIVE){ throw new IllegalArgumentException(String.format(STATUS_DUPLICATION_ERR,status));}
        logEvent(String.format("Status changed from %s to %s", this.status, BugStatus.ACTIVE));
        status = BugStatus.ACTIVE;
    }

    @Override
    public String currentStatus() {
        return status.toString();
    }

    @Override
    public void advanceStatus() {
        if(status==BugStatus.DONE){ throw new IllegalArgumentException(String.format(STATUS_DUPLICATION_ERR,status));}
        logEvent(String.format("Status changed from %s to %s", this.status, BugStatus.ACTIVE));
        status = BugStatus.ACTIVE;

    }
}
