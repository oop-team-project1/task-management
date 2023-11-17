package taskmanagement.models;

import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.ValidationHelper;

import static java.lang.String.format;
import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team
{
    public static final int NAME_LEN_MIN = 5;
    public static final int NAME_LEN_MAX = 15;

    private static final String NAME_LEN_ERR = format(
            "Team name must be between %s and %s characters long!",
            NAME_LEN_MIN,
            NAME_LEN_MAX);

    private static final String MEMBER_EXISTS_ERR = "The member you are trying to add is already part of the team!";
    public static final String MEMBER_DOES_NOT_EXIST = "The member you are trying to remove does not exists!";
    private String name;
    private List<Member> members;
    private List<Board> boards;

    public TeamImpl(String name)
    {
        setName(name);

        members = new ArrayList<>();
        boards = new ArrayList<>();
    }

    private void setName(String name)
    {
        ValidationHelper.validateStringLength(name, NAME_LEN_MIN, NAME_LEN_MAX, NAME_LEN_ERR);
        this.name = name;
    }

    //add board to team
    public void addBoard(Board board) {
        boards.add(board);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public void addMember(Member member) {
        if (members.contains(member)) throw new IllegalArgumentException(MEMBER_EXISTS_ERR);
        members.add(member);
    }
    @Override
    public void removeMember(Member member){
        if (!members.contains(member)) throw new IllegalArgumentException(MEMBER_DOES_NOT_EXIST);
        members.remove(member);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public String print() {
        StringBuilder result = new StringBuilder();
        result.append(getName()).append(System.lineSeparator());
        return result.toString();
    }
}
