package taskmanagement.models.tasks.contracts;

import taskmanagement.models.contracts.Assignable;
import taskmanagement.models.contracts.Commentable;
import taskmanagement.models.contracts.Identifiable;

import java.util.List;

public interface Task extends Identifiable, Commentable , Assignable{
    public String getTitle();

    public String getDescription();

    // TODO getComments should return a copy of the list.
    //  Separate method to print the comments in repo
    public List<String> getHistory();

    public void advanceStatus();

    public void revertStatus();


}
