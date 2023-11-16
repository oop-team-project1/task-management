package taskmanagement.commands.creation;

import com.sun.source.tree.TryTree;
import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.BugImpl;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class CreateNewBugInBoard implements Command
{
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    private static final String ERROR_MESSAGE = "Invalid priority type!";
    private final TaskManagementRepository taskManagementRepository;

    private Board board;
    private Member member;

    private String boardName;

    private String title;
    private String description;
    private String memberName;
    private Priority priority;

    public CreateNewBugInBoard(TaskManagementRepository taskManagementRepository)
    {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters)
    {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        board = taskManagementRepository.findBoardByName(boardName);
        member = taskManagementRepository.findMemberByName(memberName);

        Bug createBug = taskManagementRepository.createNewBug(title, description, member, priority);

        return String.format(CommandsConstants.BUG_CREATED_MESSAGE, createBug.getId());
    }

    private void parseParameters(List<String> parameters)
    {
        //First we will enter the name of the board where we want to add the bug
        boardName = parameters.get(0);

        //and then the bug fields for its initialization
        title = parameters.get(1);
        description = parameters.get(2);
        memberName = parameters.get(3);
        priority = ParsingHelpers.tryParseEnum(parameters.get(4), priority.getDeclaringClass(), ERROR_MESSAGE);

    }
}
