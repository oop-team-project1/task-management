package taskmanagement.models.tasks;

import taskmanagement.models.comment.Comment;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.contracts.GenericStatus;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.feedback.FeedbackStatus;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.models.tasks.enums.story.StoryStatus;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class FeedbackImpl extends TaskImpl<FeedbackStatus> implements Feedback {

    // TODO discuss rating system, it is temporary

    private final int MIN_RATING_VALUE = 1;
    private final int MAX_RATING_VALUE = 5;
    private final String RATING_VALUE_ERROR = String.format(
            "Rating should be a value between %d and %d",
            MIN_RATING_VALUE, MAX_RATING_VALUE);

    private FeedbackStatus status;
    private int rating;

    // TODO log event when constructor is used in commands/repo
    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        setRating(rating);
    }

    @Override
    protected void setStatus(FeedbackStatus status) {
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

}