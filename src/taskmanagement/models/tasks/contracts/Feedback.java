package taskmanagement.models.tasks.contracts;

import taskmanagement.models.tasks.enums.feedback.FeedbackStatus;
import taskmanagement.models.tasks.feedback.Status;

public interface Feedback extends Task{
     int getRating();
     FeedbackStatus getStatus();
    //void changeStatus(Status status);

}
