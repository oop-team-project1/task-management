package taskmanagement.commands.changing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.StoryImpl;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.models.tasks.enums.story.StoryStatus;
import taskmanagement.utils.TestHelpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeStoryStatusTest
{
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp()
    {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ChangeStoryStatus(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected()
    {
        List<String> params = TestHelpers.getList(ChangeStoryStatus.EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ChangeStoryStatus_When_ArgumentsAreValid()
    {
        List<String> params = List.of(String.valueOf(1), String.valueOf(StoryStatus.IN_PROGRESS));
        Member member = new MemberImpl(TestHelpers.getString(5));
        Story story = new StoryImpl(1,TestHelpers.getString(10), TestHelpers.getString(10), Priority.HIGH, Size.LARGE, member);

        taskManagementRepository.addMember(member);
        taskManagementRepository.addTask(story);

        command.execute(params);

        Assertions.assertEquals(StoryStatus.IN_PROGRESS, story.getStatus());
    }
}
