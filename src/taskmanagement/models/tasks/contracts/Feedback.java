package taskmanagement.models.tasks.contracts;

import taskmanagement.models.tasks.enums.feedback.FeedbackStatus;

public interface Feedback extends Task{
    public int getRating();
    public FeedbackStatus getStatus();

}
