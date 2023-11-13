package taskmanagement.models.tasks.contracts;

import taskmanagement.models.tasks.feedback.Status;

public interface Feedback extends Task{
    public int getRating();
    public Status getStatus();

}
