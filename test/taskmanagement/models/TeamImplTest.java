package taskmanagement.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.TestHelpers;

import java.util.List;

public class TeamImplTest
{
    public static final String VALID_TEAM_NAME = TestHelpers.getString(TeamImpl.NAME_LEN_MIN + 1);
    public static final String INVALID_TEAM_NAME = TestHelpers.getString(TeamImpl.NAME_LEN_MAX + 1);
    @Test
    public void constructor_Should_ThrowException_When_NameLengthOutOfBounds(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new TeamImpl(INVALID_TEAM_NAME));
    }

    @Test
    public void should_Create_Team_When_ValidValuesArePassed() {
        Team team = initializeTeam();

        Assertions.assertEquals(VALID_TEAM_NAME, team.getName());
    }

    @Test
    public void getTask_Should_ReturnCopyOfCollection_Member() {
        Team team = initializeTeam();
        List<Member> members = team.getMembers();
        List<Member> sameReference = team.getMembers();

        Assertions.assertNotSame(members, sameReference);
    }

    @Test
    public void getTask_Should_ReturnCopyOfCollection_Board() {
        Team team = initializeTeam();
        List<Board> boards = team.getBoards();
        List<Board> sameReference = team.getBoards();

        Assertions.assertNotSame(boards, sameReference);
    }

    @Test
    public void addBoard_Should_AddBoard_When_BoardIsValid(){
        Team team = initializeTeam();
        Board boardToAdd = new BoardImpl(VALID_TEAM_NAME);
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> team.addBoard(boardToAdd)),
                () -> Assertions.assertEquals(1, team.getBoards().size()));
    }

    @Test
    public void addMember_Should_AddMember_When_MemberIsValid(){
        Team team = initializeTeam();
        Member memberToAdd = new MemberImpl(VALID_TEAM_NAME);
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> team.addMember(memberToAdd)),
                () -> Assertions.assertEquals(1, team.getMembers().size()));
    }

    @Test
    public void removeMember_Should_RemoveMember_When_MemberIsValid(){
        Team team = initializeTeam();
        Member memberToRemove = new MemberImpl(VALID_TEAM_NAME);
        team.addMember(memberToRemove);
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> team.removeMember(memberToRemove)),
                () -> Assertions.assertEquals(0, team.getMembers().size()));
    }

    private static Team initializeTeam()
    {
        return new TeamImpl(VALID_TEAM_NAME);
    }
}
