package taskmanagement.commands.modifying;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.TeamImpl;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.models.tasks.StoryImpl;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.utils.TaskBaseConstants;
import taskmanagement.utils.TestHelpers;
import taskmanagement.commands.contracts.Command;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


class UnassignStoryTest {

    public static final int VALID_NAME_SIZE = 5;
    public static final int VALID_TITLE_SIZE = 10;
    public static final int VALID_DESCRIPTION_SIZE = 10;
    public static final int STORY_ID = 1;

    public static int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new UnassignStory(taskManagementRepository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestHelpers.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_RemoveStory_From_MemberTasks() {
        List<String> params = List.of(String.valueOf(TaskBaseConstants.TASK_ID), TestHelpers.getString(VALID_NAME_SIZE));
        Team team = new TeamImpl(TestHelpers.getString(VALID_NAME_SIZE));
        Member memberAssigned = new MemberImpl(TestHelpers.getString(VALID_NAME_SIZE));
        taskManagementRepository.addTeam(team);
        taskManagementRepository.addMember(memberAssigned);
        Story story = new StoryImpl(1, TestHelpers.getString(VALID_TITLE_SIZE), TestHelpers.getString(VALID_DESCRIPTION_SIZE), Priority.LOW, Size.LARGE, memberAssigned);
        taskManagementRepository.addStory(story);
        memberAssigned.addTask(story);
        command.execute(params);
        //Act and Assert
        Assertions.assertEquals(0,memberAssigned.getTask().size());

    }

    @Test
    public void should_UnassignStory_When_StoryIsAssigned() {
        //Arrange
        List<String> params = List.of(String.valueOf(TaskBaseConstants.TASK_ID), TestHelpers.getString(VALID_NAME_SIZE));
        Team team = new TeamImpl(TestHelpers.getString(VALID_NAME_SIZE));
        Member memberAssigned = new MemberImpl(TestHelpers.getString(VALID_NAME_SIZE));
        taskManagementRepository.addTeam(team);
        taskManagementRepository.addMember(memberAssigned);
        Story story = new StoryImpl(1, TaskBaseConstants.VALID_TITLE, TaskBaseConstants.VALID_DESCRIPTION, TaskBaseConstants.VALID_PRIORITY, TaskBaseConstants.VALID_SIZE, memberAssigned);
        taskManagementRepository.addStory(story);
        memberAssigned.addTask(story);
        command.execute(params);
        //Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, story::getAssignee);
    }


}