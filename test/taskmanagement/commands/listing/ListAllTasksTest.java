package taskmanagement.commands.listing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.TeamImpl;
import taskmanagement.models.contracts.Team;
import taskmanagement.models.tasks.FeedbackImpl;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.TestHelpers;

import java.util.ArrayList;
import java.util.List;

public class ListAllTasksTest
{
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp()
    {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        Task task = taskManagementRepository.createNewFeedback( TestHelpers.getString(10),
                TestHelpers.getString(10), 5);
        command = new ListAllTasks(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentForFilter()
    {
        List<String> params = TestHelpers.getList(ListAllTasks.PARAMETERS_COUNT_MAX + 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentForSort(){
        List<String> params = TestHelpers.getList(ListAllTasks.PARAMETERS_COUNT_MIN - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ShowAllTeams_When_ArgumentsAreValidForSort(){
        List<String> params = new ArrayList<>();
        params.add("sort");

        Assertions.assertDoesNotThrow(() -> command.execute(params));
    }

    @Test
    public void should_ShowAllTeams_When_ArgumentsAreValidForFilter(){
        List<String> params = new ArrayList<>();
        params.add("filter");
        params.add(TestHelpers.getString(10));

        Assertions.assertDoesNotThrow(() -> command.execute(params));
    }
}
