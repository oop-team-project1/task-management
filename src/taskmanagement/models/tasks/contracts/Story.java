package taskmanagement.models.tasks.contracts;

import taskmanagement.models.contracts.Assignable;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.story.Status;

public interface Story extends Assignable {
   public Status getStatus();
}
