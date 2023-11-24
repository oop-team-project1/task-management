package taskmanagement.commands.modifying;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
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
import taskmanagement.utils.TestHelpers;

import java.util.List;



class AssignStoryTest {
    public static final int VALID_NAME_SIZE = 5;
    public static final int VALID_TITLE_SIZE = 10;
    public static final int VALID_DESCRIPTION_SIZE=10;
    public static final int STORY_ID = 1;
    public static int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private Command command;
    private TaskManagementRepository taskManagementRepository;

    //TODO Clean up repetitive code
    @BeforeEach
    public void setUp() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new AssignStory(taskManagementRepository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestHelpers.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_AssignStory_When_StoryIsUnassigned() {
        //Arrange
        List<String> params = List.of(String.valueOf(STORY_ID),TestHelpers.getString(VALID_NAME_SIZE));
        Team team = new TeamImpl(TestHelpers.getString(VALID_NAME_SIZE));
        Member member = new MemberImpl(TestHelpers.getString(VALID_NAME_SIZE));
        taskManagementRepository.addTeam(team);
        taskManagementRepository.addMember(member);
        Story story = new StoryImpl(1,TestHelpers.getString(10), TestHelpers.getString(10), Priority.LOW,Size.LARGE);
        taskManagementRepository.addStory(story);
        //Act
        command.execute(params);
        //Assert
        Assertions.assertEquals(member,story.getAssignee());
    }
    @Test
    public void should_AddStory_To_MembersTasks() {
        List<String> params = List.of(String.valueOf(STORY_ID),TestHelpers.getString(VALID_NAME_SIZE));
        Team team = new TeamImpl(TestHelpers.getString(VALID_NAME_SIZE));
        Member member = new MemberImpl(TestHelpers.getString(VALID_NAME_SIZE));
        taskManagementRepository.addTeam(team);
        taskManagementRepository.addMember(member);
        Story story = new StoryImpl(1,TestHelpers.getString(10), TestHelpers.getString(10), Priority.LOW,Size.LARGE);
        taskManagementRepository.addStory(story);
        //Act
        command.execute(params);
        //Assert
        Assertions.assertEquals(1,member.getTask().size());
        //TODO
    }
    @Test
    public void should_Throw_When_Story_IsAssigned() {
        //Arrange
        List<String> params = List.of(String.valueOf(STORY_ID),TestHelpers.getString(VALID_NAME_SIZE));
        Team team = new TeamImpl(TestHelpers.getString(VALID_NAME_SIZE));
        Member memberAssigned = new MemberImpl(TestHelpers.getString(VALID_NAME_SIZE+2));
        Member memberTryToAssign = new MemberImpl(TestHelpers.getString(VALID_NAME_SIZE));
        taskManagementRepository.addTeam(team);
        taskManagementRepository.addMember(memberAssigned);
        taskManagementRepository.addMember(memberTryToAssign);
        Story story = new StoryImpl(1,TestHelpers.getString(VALID_TITLE_SIZE), TestHelpers.getString(VALID_DESCRIPTION_SIZE), Priority.LOW,Size.LARGE, memberAssigned);
        taskManagementRepository.addStory(story);
        //Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}




