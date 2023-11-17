package taskmanagement.models.tasks.contracts;

import taskmanagement.models.tasks.enums.feedback.FeedbackStatus;

public interface Feedback extends Task{
     int getRating();
     FeedbackStatus getStatus();
    void changeStatus(FeedbackStatus status);

    void changeRating(int rating);

}
