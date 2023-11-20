package taskmanagement.core;

import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.exceptions.ElementNotFoundException;
import taskmanagement.models.BoardImpl;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.TeamImpl;
import taskmanagement.models.contracts.*;
import taskmanagement.models.tasks.BugImpl;
import taskmanagement.models.tasks.FeedbackImpl;
import taskmanagement.models.tasks.StoryImpl;
import taskmanagement.models.tasks.TaskImpl;
import taskmanagement.models.tasks.contracts.*;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.BugStatus;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.models.tasks.enums.story.StoryStatus;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    public static final String MEMBER_DOES_NOT_EXIST = "Member %s does not exist!";
    public static final String ELEMENT_NOT_FOUND_ERR = "No record with name %s";
    public static final String ELEMENT_WITH_ID_NOT_FOUND_ERR = "No record with id %d";
    public static final String TEAM_EXISTS_ERR = "Team %s already exists!";
    public static final String MEMBER_EXISTS = "The member %s you are trying to add already exists!";
    private List<Team> teams;
    private List<Member> members;
    private List<Board> boards;
    private List<Task> tasks;
    private int id;




    public TaskManagementRepositoryImpl(){
        teams = new ArrayList<>();
        members = new ArrayList<>();
        boards = new ArrayList<>();
        tasks = new ArrayList<>();
        id = 0;
    }


    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }



    @Override
    public Member findMemberByName(String personName) {
       return this.findElementByName(getMembers(), personName);
    }
    @Override
    public Board findBoardByName(String boardName) {
        return this.findElementByName(getBoards(), boardName);
    }

    @Override
    public Team findTeamByName(String teamName) {
        return this.findElementByName(getTeams(), teamName);
    }

    //not sure if this cast is ok, but i think that there are no info that can be lost, so it should work

    @Override
    public Story findStoryById(int id)
    {
        return (Story) this.findElementById(getTasks(), id);
    }

    @Override
    public Feedback findFeedbackById(int id) {
        return (Feedback) this.findElementById(getTasks(), id);
    }
    //TODO am not a fan of the casting, will get back to it to think if there is time
    @Override
    public Bug findBugById(int id) {return (Bug) this.findElementById(getTasks(), id);}

    @Override
    public Task findTaskById(int id)
    {
        return this.findElementById(getTasks(), id);
    }


    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public Member createNewPerson(String name)
    {
        Member person = new MemberImpl(name);
        this.members.add(person);

        return person;
    }

    @Override
    public Team createNewTeam(String name) {
        Team team = new TeamImpl(name);
        teams.add(team);

        return team;

    }

    @Override
    public Board createNewBoard(String name)
    {
        Board board = new BoardImpl(name);
        this.boards.add(board);
        return board;
    }

    @Override
    public Bug createNewBugWithMember(String title, String description, Member assignee, Priority priority, Severity severity, List<String> stepsToReproduce)
    {
        Bug bug = new BugImpl(++id, title, description, assignee, priority, severity, stepsToReproduce);
        this.tasks.add(bug);
        findMemberByName(assignee.getName()).addTask(bug);
        return bug;
    }

    @Override
    public Bug createNewBugWithoutMember(String title, String description, Priority priority, Severity severity, List<String> stepsToReproduce)
    {
        Bug bug = new BugImpl(++id, title, description, priority, severity, stepsToReproduce);
        this.tasks.add(bug);
        return bug;
    }

    @Override
    public Story createNewStoryWithMember(String title, String description, Priority priority, Size size, Member assignee, StoryStatus status) {
        Story story = new StoryImpl(++id,title,description,priority,size,assignee);
        this.tasks.add(story);
        findMemberByName(assignee.getName()).addTask(story);
        return story;
    }

    @Override
    public Story createNewStoryWithoutMember(String title, String description, Priority priority, Size size, StoryStatus status) {
        Story story = new StoryImpl(++id,title,description,priority,size);
        this.tasks.add(story);
        return story;
    }

    @Override
    public Feedback createNewFeedback(String title, String description, int rating) {
        Feedback feedback = new FeedbackImpl(++id, title,description,rating);
        //TODO: check the implementation
        this.tasks.add(feedback);
        return feedback;
    }

    @Override
    public void addTeam(Team team) {
        if (teams.contains(team)) throw new IllegalArgumentException(String.format(TEAM_EXISTS_ERR,team.getName()));
        teams.add(team);
    }

    @Override
    public void addMember(Member member) {
        if (members.contains(member)) throw new IllegalArgumentException(String.format(MEMBER_EXISTS, member.getName()));
        members.add(member);
    }

    @Override
    public void addBoard(Board board) {
        if (boards.contains(board)) throw new IllegalArgumentException(String.format(MEMBER_EXISTS, board.getName()));
        boards.add(board);
    }

    @Override
    public void addTask(Task task) {
        if (tasks.contains(task)) throw new IllegalArgumentException(String.format(MEMBER_EXISTS, task.getTitle()));
        tasks.add(task);
    }

    @Override
    public void addMemberToTeam(Member memberToAdd, Team team) {
        if(!teams.contains(team)) throw new IllegalArgumentException("Team does not exist!");
        team.addMember(memberToAdd);
    }

    @Override
    public void removeMemberFromTeam(Member memberToRemove, Team team) {
        if(!teams.contains(team)) throw new IllegalArgumentException("Team does not exist!");
        team.removeMember(memberToRemove);
    }

    private <T extends IdentifiableByName> T findElementByName(List<T> elements, String name) {
        for (T element : elements) {
            if (element.getName().equals(name)) {
                return element;
            }
        }

        throw new ElementNotFoundException(String.format(ELEMENT_NOT_FOUND_ERR, name));
    }

    private <T extends Identifiable> T findElementById(List<T> elements, int id) {
        for (T element : elements) {
            if (element.getId() == id) {
                return element;
            }
        }

        throw new ElementNotFoundException(String.format(ELEMENT_WITH_ID_NOT_FOUND_ERR, id));
    }
}
