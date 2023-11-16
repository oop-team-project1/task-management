package taskmanagement.core;

import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Board;
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
        return null;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public Member findMemberByName(String personName) {
        for(Member member : getMembers()) {
            if(member.getName().equalsIgnoreCase(personName)) {
                return member;
            }
        }

        throw new IllegalArgumentException(String.format(MEMBER_DOES_NOT_EXIST, personName));
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
}
