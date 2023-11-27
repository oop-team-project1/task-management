package taskmanagement.commands.creation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.TaskManagementRepositoryImpl;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.BoardImplTest;
import taskmanagement.models.MemberImplTest;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.utils.TaskBaseConstants;
import taskmanagement.utils.TestHelpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateStoryTest {

    private static final int MAX_NUMBER_OF_ARGUMENTS = 6;

    private Command command;
    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    public void setUp() {
        this.taskManagementRepository = new TaskManagementRepositoryImpl();
        this.command = new CreateStory(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestHelpers.getList(MAX_NUMBER_OF_ARGUMENTS + 1);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }


    @Test
    public void execute_Should_CreateNewStoryInBoard_When_PassedValidInput() {

        Board board = BoardImplTest.initializeBoard();
        Member member = MemberImplTest.initializeMember();
        taskManagementRepository.addBoard(board);
        taskManagementRepository.addMember(member);

        List<String> params = List.of(board.getName(),
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.VALID_PRIORITY.toString(),
                TaskBaseConstants.VALID_SIZE.toString());


        command.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getTasks().size());
    }

    @Test
    public void execute_Should_CreateNewStoryInBoard_When_PassedValidInput_WithMember() {

        Board board = BoardImplTest.initializeBoard();
        Member member = MemberImplTest.initializeMember();
        taskManagementRepository.addBoard(board);
        taskManagementRepository.addMember(member);

        List<String> params = List.of(board.getName(),
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                member.getName(),
                TaskBaseConstants.VALID_PRIORITY.toString(),
                TaskBaseConstants.VALID_SIZE.toString());


        command.execute(params);

        Assertions.assertEquals(1, taskManagementRepository.getTasks().size());
    }


}