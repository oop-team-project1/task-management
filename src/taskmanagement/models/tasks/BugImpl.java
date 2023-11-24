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
    public static final String BUG_ASSIGNED_ERR =
            "This bug is already assigned to %s!\n" +
            "If you are sure you wish to assign it to %s, please unassign it first by using the command UnassignBug %d %s";
    public static final String MEMBER_NOT_ASSIGNED_ERR = "Member %s is not assigned to this task therefore can't be unassigned!";

    private Member assignee;
    private BugStatus status;
    private Priority priority;
    private Severity severity;
    private List<String> stepsToReproduce;
    private boolean isAssigned = false;


    public BugImpl(int id, String title, String description, Priority priority, Severity severity, List<String> stepsToReproduce) {
        super(id, title, description);
        this.priority = priority;
        this.severity = severity;
        setStepsToReproduce(stepsToReproduce);
        this.status = BugStatus.ACTIVE;
    }

    public BugImpl(int id, String title, String description, Member assignee, Priority priority, Severity severity, List<String> stepsToReproduce) {
        this(id, title, description, priority, severity, stepsToReproduce);
        setAssignee(assignee);
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
        //TODO write message
        if (isAssigned) {
            throw new IllegalArgumentException(
                    String.format(BUG_ASSIGNED_ERR, assignee.getName(),member.getName(),getId(),assignee));
        }
        logEvent(String.format("Bug has been assigned to %s",member.getName()));
        assignee = member;
        isAssigned = true;
    }

    @Override
    public void removeAssignee(Member member)
    {
        //TODO overwrite equals in member

        if (!assignee.equals(member)) {
            throw new IllegalArgumentException(MEMBER_NOT_ASSIGNED_ERR);
        }
        logEvent(String.format("Member %s was unassigned from this task", assignee.getName()));
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

    private void setStepsToReproduce(List<String> stepsToReproduce) {
        if(stepsToReproduce.isEmpty()) throw new IllegalArgumentException("List of steps to reproduce the bug can't be empty!");
        this.stepsToReproduce = new ArrayList<>(stepsToReproduce);
    }
    private String printSteps(List<String> stepsToReproduce){
        StringBuilder result = new StringBuilder();
        for(String steps : stepsToReproduce) {
            result.append(steps).append(System.lineSeparator());
        }
        return result.toString().trim();
    }

}
