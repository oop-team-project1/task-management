package taskmanagement.core.contracts;

import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.models.tasks.TaskImpl;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.BugStatus;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.models.tasks.enums.story.StoryStatus;

import java.util.List;


public interface TaskManagementRepository
{
    List<Team> getTeams();

    List<Member> getMembers();
    List<Board> getBoards();
    List<Task> getTasks();

    Member createNewPerson(String name);
    Member findMemberByName(String personName);
    Board findBoardByName(String boardName);
    Team findTeamByName(String teamName);
    Story findStoryById(int id);
    Task findTaskById(int id);

    Feedback findFeedbackById(int id);

    Bug findBugById(int id);


    Team createNewTeam(String name);
    Board createNewBoard(String name);

    Bug createNewBugWithMember(String title, String description, Member assignee, Priority priority, Severity severity, List<String> stepsToReproduce);
    Bug createNewBugWithoutMember(String title, String description, Priority priority, Severity severity, List<String> stepsToReproduce);
    Story createNewStoryWithMember(String title, String description, Priority priority, Size size, Member assignee, StoryStatus status);
    Story createNewStoryWithoutMember(String title, String description, Priority priority, Size size, StoryStatus status);
    Feedback createNewFeedback(String title, String description, int rating);

    void addTeam(Team teamToAdd);
    void addMember(Member member);
    void addBoard(Board board);
    void addTask(Task task);
    void addMemberToTeam(Member memberToAdd, Team team);
    void removeMemberFromTeam(Member memberToRemove, Team team);


}
