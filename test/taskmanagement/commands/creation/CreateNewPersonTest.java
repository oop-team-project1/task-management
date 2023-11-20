package taskmanagement.commands.creation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.utils.TestHelpers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

public class CreateNewPersonTest
{
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp()
    {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateNewPerson(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected()
    {
        List<String> params = TestHelpers.getList(CreateNewPerson.EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DifferentNameLenght()
    {
        List<String> params = List.of(TestHelpers.getString(4));

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_CreateNewPerson_When_PassedValidInput()
    {
        List<String> params = List.of(TestHelpers.getString(5));

        command.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getMembers().size());
    }
}
