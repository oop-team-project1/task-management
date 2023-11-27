package taskmanagement.models.tasks;

import taskmanagement.models.MemberImpl;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.models.tasks.enums.story.StoryStatus;

public class StoryImpl extends TaskImpl implements Story {
    public static final String PRIORITY_CHANGE_ERR = "Priority already set to %s. " +
                                                     "If you still wish to change it, " +
                                                     "please provide a valid value.";

    public static final String SIZE_CHANGE_ERR = "Size already set to %s. " +
                                                 "If you still wish to change it, " +
                                                 "please provide a valid value.";

    public static final String STORY_ASSIGNED_ERR =
            "This story is already assigned to %s!\n" +
            "If you are sure you wish to assign it to %s, please unassign it first by using the command UnassignStory %d %s";

    public static final String MEMBER_NOT_ASSIGNED_ERR = "Member %s is not assigned to this task therefore can't be unassigned!";
    public static final String TASK_NOT_ASSIGNED = "This task is not assigned to anyone!";

    private Priority priority;
    private Size size;
    private StoryStatus status;
    private Member assignee;
    private boolean isAssigned = false;
    //TODO Implement in parent class with LogEvents


    public StoryImpl(int id, String title, String description, Priority priority, Size size) {
        super(id, title, description);
        this.priority = priority;
        this.size = size;
        this.status = StoryStatus.NOT_DONE;

    }

    public StoryImpl(int id, String title, String description, Priority priority, Size size, Member assignee) {
        this(id, title, description, priority, size);
        setAssignee(assignee);
    }


    @Override
    public Member getAssignee() {
        if (!isAssigned) throw new IllegalArgumentException(TASK_NOT_ASSIGNED);
        return new MemberImpl(assignee);

    }

    public void setAssignee(Member member) {
        if (isAssigned) {
            throw new IllegalArgumentException(
                    String.format(STORY_ASSIGNED_ERR, assignee.getName(), member.getName(), getId(), assignee));

        }
        logEvent(String.format("Story has been assigned to %s", member.getName()));
        assignee = member;
        isAssigned = true;
    }

    @Override
    public void removeAssignee(Member member) {
        if (!assignee.equals(member)) {
            throw new IllegalArgumentException(MEMBER_NOT_ASSIGNED_ERR);
        }
        logEvent(String.format("Member %s was unassigned from this task", member.getName()));
        assignee = null;
        isAssigned = false;


    }

    @Override
    public boolean isAssigned() {
        return isAssigned;
    }

    private void setStatus(StoryStatus status) {
        logEvent(String.format("Status changed from %s to %s", this.getStatus(), status));
        this.status = status;
    }

    @Override
    public StoryStatus getStatus() {
        return status;
    }


    public Size getSize() {
        return size;
    }

    @Override
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
    public void changeSize(Size newSize) {
        if (this.size == newSize) {
            throw new IllegalArgumentException(
                    String.format(SIZE_CHANGE_ERR, priority));
        }
        logEvent(String.format("Size changed from %s to %s", this.size, newSize));
        this.size = newSize;
    }

    @Override
    public void changeStatus(StoryStatus newStatus) {
        if (this.status == newStatus) {
            throw new IllegalArgumentException(
                    String.format(PRIORITY_CHANGE_ERR, priority));
        }
        setStatus(newStatus);
    }
}
