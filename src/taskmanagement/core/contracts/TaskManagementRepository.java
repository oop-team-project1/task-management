package taskmanagement.core.contracts;

import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.models.tasks.enums.story.Size;


import java.util.List;


public interface TaskManagementRepository {
    List<Team> getTeams();

    List<Member> getMembers();

    List<Board> getBoards();

    List<Task> getTasks();
    List<Bug> getBugs();
    List<Feedback> getFeedbacks();
    List<Story> getStories();

    Member createNewPerson(String name);

    Member findMemberByName(String personName);

    Board findBoardByName(String boardName);

    Team findTeamByName(String teamName);

    Story findStoryById(int id);

    Story findStoryById(int id, String errorMessage);

    Task findTaskById(int id);

    Feedback findFeedbackById(int id);

    Bug findBugById(int id);

    Bug findBugById(int id, String errorMessage);

    Team createNewTeam(String name);

    Board createNewBoard(String name);

    Bug createNewBugWithMember(String title, String description, Member assignee, Priority priority, Severity severity, List<String> stepsToReproduce);

    Bug createNewBugWithoutMember(String title, String description, Priority priority, Severity severity, List<String> stepsToReproduce);

    Story createNewStoryWithMember(String title, String description, Member assignee, Priority priority, Size size);

    Story createNewStoryWithoutMember(String title, String description, Priority priority, Size size);

    Feedback createNewFeedback(String title, String description, int rating);

    void addTeam(Team teamToAdd);

    void addMember(Member member);

    void addBoard(Board board);

    void addTask(Task task);

    void addBug(Bug bug);

    void addStory(Story story);

    void addFeedback(Feedback feedback);

    void addMemberToTeam(Member memberToAdd, Team team);

    void removeMemberFromTeam(Member memberToRemove, Team team);


}
