package taskmanagement.models.contracts;

import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;

import java.util.List;

public interface Team extends IdentifiableByName{



    List<Member> getMembers();

    List<Board> getBoards();


}
