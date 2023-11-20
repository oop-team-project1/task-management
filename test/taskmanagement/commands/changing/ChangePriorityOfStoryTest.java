package taskmanagement.commands.changing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.commands.modifying.AddCommentToTask;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.BugImpl;
import taskmanagement.models.tasks.StoryImpl;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.utils.TestHelpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangePriorityOfStoryTest

{
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp()
    {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ChangePriorityOfStory(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected()
    {
        List<String> params = TestHelpers.getList(ChangePriorityOfStory.EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ChangePriorityOfStory_When_ArgumentsAreValid()
    {
        List<String> params = List.of(String.valueOf(1), String.valueOf(Priority.LOW));
        Member member = new MemberImpl(TestHelpers.getString(5));
        Story story = new StoryImpl(1,TestHelpers.getString(10), TestHelpers.getString(10), Priority.HIGH, Size.LARGE, member);

        taskManagementRepository.addMember(member);
        taskManagementRepository.addTask(story);

        command.execute(params);

        Assertions.assertEquals(Priority.LOW, story.getPriority());
    }
}
