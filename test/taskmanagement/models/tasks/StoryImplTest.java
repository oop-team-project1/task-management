package taskmanagement.models.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.MemberImplTest;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.models.tasks.enums.story.StoryStatus;
import taskmanagement.utils.TaskBaseConstants;


public class StoryImplTest {

    @Test
    void storyImpl_Should_ImplementStoryInterface() {
        StoryImpl story = initializeTestStory();
        Assertions.assertTrue(story instanceof Story);
    }

    @Test
    void storyImpl_Should_Throw_When_TitleIsInvalid() {

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new StoryImpl(TaskBaseConstants.TASK_ID,
                        TaskBaseConstants.INVALID_TITLE,
                        TaskBaseConstants.VALID_DESCRIPTION,
                        TaskBaseConstants.VALID_PRIORITY,
                        TaskBaseConstants.VALID_SIZE));
    }

    @Test
    void getAssignee_Should_Return_Copy_Of_Assignee() {
        StoryImpl story = initializeTestStory();
        story.getAssignee().addTask(initializeTestStory());
        Assertions.assertEquals(0, story.getAssignee().getTask().size());
    }

    @Test
    void setAssignee_Should_Throw_When_TaskIsAssignedAlready() {
        StoryImpl story = initializeTestStory();
        Member member = new MemberImpl("xxxxx");
        Assertions.assertThrows(IllegalArgumentException.class, () -> story.setAssignee(member));

    }

    @Test
    void removeAssignee_Should_Throw_When_InputDoesNotMatchAssignedMember() {
        StoryImpl story = initializeTestStory();
        Member member = new MemberImpl("xxxxx");
        Assertions.assertThrows(IllegalArgumentException.class, () -> story.removeAssignee(member));
    }

    @Test
    void changePriority_Should_Throw_When_PriorityAlreadySetToGivenInput() {
        Story story = initializeTestStory();
        Assertions.assertThrows(IllegalArgumentException.class, () -> story.changePriority(TaskBaseConstants.VALID_PRIORITY));
    }

    @Test
    void changePriority_Should_LogEvent() {
        Story story = initializeTestStory();
        story.changePriority(Priority.MEDIUM);
        Assertions.assertEquals(2, story.getHistory().size());
    }

    @Test
    void changeSize_Should_LogEvent() {
        Story story = initializeTestStory();
        story.changeSize(Size.LARGE);
        Assertions.assertEquals(2, story.getHistory().size());
    }

    @Test
    void changeSize_Should_Throw_When_SizeAlreadySetToGivenInput() {
        Story story = initializeTestStory();
        Assertions.assertThrows(IllegalArgumentException.class, () -> story.changeSize(TaskBaseConstants.VALID_SIZE));
    }

    @Test
    void changeStatus_Should_Throw_When_SizeAlreadySetToGivenInput() {
        Story story = initializeTestStory();
        Assertions.assertThrows(IllegalArgumentException.class, () -> story.changeStatus(StoryStatus.NOT_DONE));
    }

    public static StoryImpl initializeTestStory() {
        Member member = MemberImplTest.initializeMember();
        return new StoryImpl(TaskBaseConstants.TASK_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.VALID_PRIORITY,
                TaskBaseConstants.VALID_SIZE,
                member);
    }
}