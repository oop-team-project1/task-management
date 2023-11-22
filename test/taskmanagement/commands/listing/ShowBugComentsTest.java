package taskmanagement.commands.listing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.commands.creation.CreateNewBugInBoardTest;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.BugImpl;
import taskmanagement.models.tasks.StoryImpl;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.utils.TestHelpers;

import java.util.ArrayList;
import java.util.List;

public class ShowBugComentsTest {
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ShowBugComments(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected(){
        List<String> params = TestHelpers.getList(ShowBugComments.EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ShowBugComments_When_ArgumentsAreValid(){
                Bug bug = new BugImpl(1,
                CreateNewBugInBoardTest.VALID_BUG_TITLE,
                CreateNewBugInBoardTest.VALID_BUG_DESCRIPTION,
                Priority.LOW,
                Severity.CRITICAL,
                TestHelpers.getList(5));
        taskManagementRepository.addBug(bug);
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(1));

        Assertions.assertDoesNotThrow(() -> command.execute(params));
    }
}
