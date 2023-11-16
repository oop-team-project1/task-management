package taskmanagement.models.contracts;

import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;

import java.util.List;

public interface Team extends IdentifiableByName{

    void addBoard(Board board);

    List<Member> getMembers();

    List<Board> getBoards();


}
