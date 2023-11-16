package taskmanagement.models.contracts;

import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Printable;

import java.util.List;

public interface Team extends IdentifiableByName, Printable {

    String getName();

    List<Member> getMembers();

    List<Board> getBoards();

    void addMember(Member member);

    void removeMember(Member member);


}
