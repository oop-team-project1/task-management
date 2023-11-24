package taskmanagement.models.tasks.contracts;

import taskmanagement.models.comment.Comment;
import taskmanagement.models.contracts.Commentable;
import taskmanagement.models.contracts.Identifiable;

import java.util.List;

public interface Task extends Identifiable, Commentable{
    String getTitle();

    String getDescription();

    // TODO getComments should return a copy of the list.
    //  Separate method to print the comments in repo
    List<String> getHistory();

    void addComment(Comment comment);

    void removeComment(Comment comment);

    String showComments();

    String print();

}
