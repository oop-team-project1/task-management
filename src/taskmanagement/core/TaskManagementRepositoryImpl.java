package taskmanagement.core;

import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.exceptions.ElementNotFoundException;
import taskmanagement.models.BoardImpl;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.contracts.*;
import taskmanagement.models.tasks.BugImpl;
import taskmanagement.models.tasks.TaskImpl;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.models.tasks.enums.story.StoryStatus;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    public static final String MEMBER_DOES_NOT_EXIST = "Member %s does not exist!";
    public static final String ELEMENT_NOT_FOUND_ERR = "No record with name %s";
    public static final String ELEMENT_WITH_ID_NOT_FOUND_ERR = "No record with id %d";
    private List<Team> teams;
    private List<Member> members;
    private List<Board> boards;
    private List<TaskImpl> tasks;
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
    public Task findTaskById(int id)
    {
        return this.findElementById(getTasks(), id);
    }

    @Override
    public Board findBoardByName(String boardName) {
        return this.findElementByName(getBoards(), boardName);
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
        return null;
    }

    @Override
    public Board createNewBoard(String name)
    {
        Board board = new BoardImpl(name);
        this.boards.add(board);

        return board;
    }

    @Override
    public Bug createNewBug(String title, String description, Member assignee, Priority priority)
    {
        Bug bug = new BugImpl(++id, title, description, assignee, priority);

        for (int i = 0; i < members.size(); i++)
        {
           if (members.get(i).equals(assignee))
           {
               members.get(i).addTask(bug);
               break;
           }
        }

        return bug;
    }

    @Override
    public Story createNewStory(String title, String description, Priority priority, Size size, Member assignee, StoryStatus status) {
        return null;
    }

    @Override
    public Feedback createNewFeedback(String title, String description, int rating) {
        return null;
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
