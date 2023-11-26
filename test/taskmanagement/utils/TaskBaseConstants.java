package taskmanagement.utils;

import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.models.tasks.enums.story.Size;

public class TaskBaseConstants {

    public static final int TASK_ID = 1;
    public static final int TITLE_MIN_LENGHT = 10;
    public static final int DESCRIPTION_MIN_LENGTH = 10;

    public static final String VALID_TITLE = TestHelpers.getString(TITLE_MIN_LENGHT);
    public static final String VALID_DESCRIPTION = TestHelpers.getString(DESCRIPTION_MIN_LENGTH);
    public static final String INVALID_TITLE = TestHelpers.getString(TITLE_MIN_LENGHT - 1);

    public static final Priority VALID_PRIORITY = Priority.HIGH;
    public static final Size VALID_SIZE = Size.MEDIUM;
    public static final Severity VALID_SEVERITY = Severity.MAJOR;


}
