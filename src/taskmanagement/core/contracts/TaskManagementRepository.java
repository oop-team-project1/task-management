package taskmanagement.core.contracts;

import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.models.tasks.enums.story.StoryStatus;

import java.util.List;


public interface TaskManagementRepository
{
    List<Team> getTeams();

    List<Member> getMembers();
    List<Board> getBoards();

    Member createNewPerson(String name);

    Team createNewTeam(String name);
    Board createNewBoard(String name);

    Bug createNewBug(String title, String description, Member assignee, Priority priority);
    Story createNewStory(String title, String description, Priority priority, Size size, Member assignee, StoryStatus status);
    Feedback createNewFeedback(String title, String description, int rating);



}
