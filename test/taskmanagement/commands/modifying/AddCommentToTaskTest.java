package taskmanagement.commands.modifying;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.BugImpl;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.utils.TestHelpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommentToTaskTest
{
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @Before
    public void setUp()
    {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new AddCommentToTask(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected()
    {
        List<String> params = TestHelpers.getList(AddCommentToTask.EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_AddCommentToTask_When_ArgumentsAreValid()
    {
        List<String> params = List.of(String.valueOf(1), TestHelpers.getString(5), TestHelpers.getString(5));
        Member member = new MemberImpl(TestHelpers.getString(5));
        Task task = new BugImpl(1,TestHelpers.getString(10), TestHelpers.getString(10), member, Priority.LOW, Severity.CRITICAL, TestHelpers.getList(10));

        taskManagementRepository.addMember(member);
        taskManagementRepository.addTask(task);

        command.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getTasks().get(0).getComments().size());
    }
}
