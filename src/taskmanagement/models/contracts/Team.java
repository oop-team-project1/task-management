package taskmanagement.models.contracts;

import taskmanagement.models.tasks.contracts.Printable;

import java.util.List;

public interface Team extends IdentifiableByName, Printable {
    void addBoard(Board board);
    String getName();

    List<Member> getMembers();

    List<Board> getBoards();

    void addMember(Member member);

    void removeMember(Member member);

}
