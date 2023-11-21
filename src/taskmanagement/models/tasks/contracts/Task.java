package taskmanagement.models.tasks.contracts;

import taskmanagement.models.comment.Comment;
import taskmanagement.models.contracts.Assignable;
import taskmanagement.models.contracts.Commentable;
import taskmanagement.models.contracts.Identifiable;
import taskmanagement.models.tasks.enums.bug.BugStatus;

import java.util.List;

public interface Task extends Identifiable, Commentable{
    public String getTitle();

    public String getDescription();

    // TODO getComments should return a copy of the list.
    //  Separate method to print the comments in repo
    public List<String> getHistory();

    public void addComment(Comment comment);

    public void removeComment(Comment comment);

    String showComments();

}
