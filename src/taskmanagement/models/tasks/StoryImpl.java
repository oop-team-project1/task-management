package taskmanagement.models.tasks;

import taskmanagement.models.MemberImpl;
import taskmanagement.models.comment.Comment;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.GenericStatus;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.models.tasks.enums.story.StoryStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class StoryImpl extends TaskImpl implements Story {
    public static final String PRIORITY_CHANGE_ERR = "Priority already set to %s. " +
                                                     "If you still wish to change it, " +
                                                     "please provide a valid value.";

    public static final String SIZE_CHANGE_ERR = "Size already set to %s. " +
                                                "If you still wish to change it, " +
                                                "please provide a valid value.";
    private Priority priority;
    private Size size;
    private StoryStatus status;
    private Member assignee;

    //TODO Implement in parent class with LogEvents


    public StoryImpl(int id, String title, String description, Priority priority, Size size, Member assignee, StoryStatus status) {
        super(id, title, description);
        this.priority = priority;
        this.size = size;
        this.status = status;
        this.assignee = assignee;

    }

    public StoryImpl(int id, String title, String description, Priority priority, Size size, Member assignee) {
        super(id, title, description);
        this.priority = priority;
        this.size = size;
        this.assignee = assignee;

    }


    @Override
    public Member getAssignee() {
        //TODO implement cloneable in MemberImpl || implement to return constructor
        // with getters
        return assignee;

    }


    // TODO implement LogEvent class
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
        logEvent(String.format("Status changed from %s to %s", this.status, newStatus));
        this.status = newStatus;
    }
}
