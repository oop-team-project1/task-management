package taskmanagement.commands.creation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.utils.TestHelpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateFeedbackInBoardTest {
    private Command command;
    private TaskManagementRepository taskManagementRepository;
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    @BeforeEach
    public void setUp()
    {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateFeedbackInBoard(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected()
    {
        List<String> params = TestHelpers.getList( EXPECTED_NUMBER_OF_ARGUMENTS+ 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

}
