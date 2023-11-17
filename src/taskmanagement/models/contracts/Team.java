package taskmanagement.models.contracts;

import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Printable;

import java.util.List;

public interface Team extends IdentifiableByName, Printable {

//<<<<<<< create-board-in-a-team-command
    void addBoard(Board board);
//=======
    String getName();
//>>>>>>> command-implementation

    List<Member> getMembers();

    List<Board> getBoards();

    void addMember(Member member);

    void removeMember(Member member);


}
