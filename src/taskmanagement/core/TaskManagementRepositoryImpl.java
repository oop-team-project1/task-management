package taskmanagement.core;

import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.exceptions.ElementNotFoundException;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.IdentifiableByName;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.contracts.Feedback;
import taskmanagement.models.tasks.contracts.Story;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.story.Size;
import taskmanagement.models.tasks.enums.story.StoryStatus;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    public static final String MEMBER_DOES_NOT_EXIST = "Member %s does not exist!";
    public static final String ELEMENT_NOT_FOUND_ERR = "No record with name %s";
    private List<Team> teams;

    private List<Member> members;
    private List<Board> boards;
    private int Id;




    public TaskManagementRepositoryImpl(){
        teams = new ArrayList<>();
        Id = 0;
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
    public Member findMemberByName(String personName) {
       return this.findElementByName(getMembers(), personName);
    }

    @Override
    public Team findTeamByName(String teamName) {
        return this.findElementByName(getTeams(), teamName);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public Member createNewPerson(String name) {
        return null;
    }

    @Override
    public Team createNewTeam(String name) {
        return null;
    }

    @Override
    public Board createNewBoard(String name) {
        return null;
    }

    @Override
    public Bug createNewBug(String title, String description, Member assignee, Priority priority) {
        return null;
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
}
