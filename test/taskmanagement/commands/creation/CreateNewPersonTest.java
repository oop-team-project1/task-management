package taskmanagement.commands.creation;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.utils.TestHelpers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

public class CreateNewPersonTest
{
    public static final int EXPECTED_NUMBER_OF_PARAMS = 1;

    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @Before
    public void setUp()
    {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateNewPerson(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected()
    {
        List<String> params = TestHelpers.getList(EXPECTED_NUMBER_OF_PARAMS + 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DifferentNameLenght()
    {
        List<String> params = List.of("xxxx");

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_CreateNewPerson_When_PassedValidInput()
    {
        List<String> params = List.of("xxxxx");

        command.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getMembers().size());
    }
}
