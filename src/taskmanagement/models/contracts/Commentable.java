package taskmanagement.models.contracts;

import taskmanagement.models.comment.Comment;

import java.util.List;

public interface Commentable {
    List<Comment> getComments();
}
