package taskmanagement.commands.changing;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.StoryImpl;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.utils.TestHelpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeSizeOfStoryTest
{
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @Before
    public void setUp()
    {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new ChangeSizeOfStory(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected()
    {
        List<String> params = TestHelpers.getList(ChangeSizeOfStory.EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ChangeSizeOfStory_When_ArgumentsAreValid()
    {
        List<String> params = List.of(String.valueOf(1), String.valueOf(Size.SMALL));
        Member member = new MemberImpl(TestHelpers.getString(5));
        Story story = new StoryImpl(1,TestHelpers.getString(10), TestHelpers.getString(10), Priority.HIGH, Size.LARGE, member);

        taskManagementRepository.addMember(member);
        taskManagementRepository.addTask(story);

        command.execute(params);

        Assertions.assertEquals(Size.SMALL, story.getSize());
    }
}
