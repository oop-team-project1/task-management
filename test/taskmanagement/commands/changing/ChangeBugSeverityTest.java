package taskmanagement.commands.changing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.tasks.BugImpl;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.utils.TaskBaseConstants;
import taskmanagement.utils.TestHelpers;

import java.util.List;



class ChangeBugSeverityTest {
    public final int EXPECTED_NUMBER_ARGUMENTS = 2;
    public static final List<String> stepsToReproduce = List.of(
            "1.Load the program.", "2.Press the big red button", "3.Wait for the launch", "4.Get an error instead.");

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    void setUp() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ChangeBugSeverity(taskManagementRepository);
    }

    @Test
    void should_Throw_When_ArgumentCountIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(TestHelpers.getList(EXPECTED_NUMBER_ARGUMENTS - 1)));
    }

    @Test
    void should_ChangeBugPriority_When_ArgumentsAreValid() {
        List<String> params = List.of(String.valueOf(1), String.valueOf(Severity.MINOR));
        Bug bug = new BugImpl(TaskBaseConstants.TASK_ID, TaskBaseConstants.VALID_TITLE, TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.VALID_PRIORITY, TaskBaseConstants.VALID_SEVERITY, stepsToReproduce);
        taskManagementRepository.addBug(bug);
        command.execute(params);
        Assertions.assertEquals(Severity.MINOR, bug.getSeverity());
    }

}