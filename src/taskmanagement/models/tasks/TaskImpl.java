package taskmanagement.models.tasks;

import taskmanagement.models.comment.Comment;
import taskmanagement.models.comment.CommentImpl;
import taskmanagement.models.tasks.contracts.GenericStatus;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.models.tasks.enums.bug.BugStatus;
import taskmanagement.utils.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskImpl implements Task {

    private final int MIN_TITLE_LENGTH = 10;
    private final int MAX_TITLE_LENGTH = 100;
    private final String TITLE_LENGTH_ERROR = String.format("Title should be between %d and %d symbols!", MIN_TITLE_LENGTH, MAX_TITLE_LENGTH);
    private final int MIN_DESCRIPTION_LENGTH = 10;
    private final int MAX_DESCRIPTION_LENGTH = 100;
    private final String DESCRIPTION_LENGTH_ERROR = String.format("Description should be between %d and %d symbols!", MIN_DESCRIPTION_LENGTH, MAX_DESCRIPTION_LENGTH);

    private final int id;
    private String title;
    private String description;
    private final List<Comment> comments;
    private final List<String> history;

    public TaskImpl(int id, String title, String description) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        comments = new ArrayList<>();
        history = new ArrayList<>();

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        validateTitle(title);
        this.title = title;

    }

    @Override
    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    protected void validateTitle(String title) {
        ValidationHelper.validateStringLength(title, MIN_TITLE_LENGTH, MAX_TITLE_LENGTH, TITLE_LENGTH_ERROR);
    }

    protected void validateDescription(String description) {
        ValidationHelper.validateStringLength(description, MIN_DESCRIPTION_LENGTH, MAX_DESCRIPTION_LENGTH, DESCRIPTION_LENGTH_ERROR);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public void logEvent(String event) {
        history.add(event);
    }

    @Override
    public List<String> getHistory() {
        return new ArrayList<>(history);

    }

    @Override
    public void addComment(Comment comment){
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment){
        comments.remove(comment);
    }

    //TODO Write toString method


}