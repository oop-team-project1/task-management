package taskmanagement.models.tasks;

import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.enums.feedback.FeedbackStatus;
import taskmanagement.utils.ValidationHelper;


public class FeedbackImpl extends TaskImpl implements Feedback {

    public final int MIN_RATING_VALUE = 1;
    public final int MAX_RATING_VALUE = 5;
    public final String RATING_VALUE_ERROR = String.format(
            "Rating should be a value between %d and %d",
            MIN_RATING_VALUE, MAX_RATING_VALUE);
    public static final String STATUS_CHANGE_ERR = "Status already set to %s. " +
            "If you still wish to change it, " +
            "please provide a valid value.";

    private FeedbackStatus status;
    private int rating;

    // TODO log event when constructor is used in commands/repo
    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        setRating(rating);
        this.status = FeedbackStatus.NEW;
    }

    private void setStatus(FeedbackStatus status) {
        logEvent(String.format("Status changed from %s to %s", this.getStatus(), status));
        this.status = status;
    }

    private void setRating(int rating) {
        ValidationHelper.validateValueInRange(
                rating, MIN_RATING_VALUE,
                MAX_RATING_VALUE, RATING_VALUE_ERROR);
        this.rating = rating;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public FeedbackStatus getStatus() {
        return status;
    }


    @Override
    public void changeStatus(FeedbackStatus status) {
        if (this.status == status) {
            throw new IllegalArgumentException(
                    String.format(STATUS_CHANGE_ERR, status));
        }
        setStatus(status);
    }


    @Override
    public void changeRating(int rating) {
        int currentRating = this.getRating();
        setRating(rating);
        logEvent(String.format("Rating changed from %d to %d", currentRating, rating));
    }
}
