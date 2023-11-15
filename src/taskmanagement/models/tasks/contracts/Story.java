package taskmanagement.models.tasks.contracts;

import taskmanagement.models.contracts.Assignable;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.models.tasks.enums.story.StoryStatus;

public interface Story extends Assignable,Prioritizable {
   public StoryStatus getStatus();
   public Size getSize();
}
