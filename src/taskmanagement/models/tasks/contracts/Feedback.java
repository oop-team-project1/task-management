package taskmanagement.models.tasks.contracts;

import taskmanagement.models.tasks.enums.feedback.FeedbackStatus;

public interface Feedback extends Task, GenericStatus<FeedbackStatus>{
     int getRating();
     FeedbackStatus getStatus();
    void changeRating(int rating);

}
