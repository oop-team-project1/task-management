package taskmanagement.models.contracts;

import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;

import java.util.List;

public interface Team {

    public String getName();

    public List<Member> getMembers();

    public List<Board> getBoards();


}
