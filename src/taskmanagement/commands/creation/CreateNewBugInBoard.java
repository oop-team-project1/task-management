package taskmanagement.commands.creation;

import com.sun.source.tree.TryTree;
import org.w3c.dom.ls.LSLoadEvent;
import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.BoardImpl;
import taskmanagement.models.MemberImpl;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.BugImpl;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.BugStatus;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class CreateNewBugInBoard implements Command
{
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;
    private static final String INVALID_PRIORITY = "Invalid priority type!";
    private static final String INVALID_SEVERITY = "Invalid severity type!";
    private final TaskManagementRepository taskManagementRepository;

    private Board board;
    private Member member;

    private String boardName;

    private String title;
    private String description;
    private String memberName;
    private Priority priority;
    private Severity severity;
    private String steps;
    private List<String> stepsToReproduce;

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

        Bug createBug = taskManagementRepository.createNewBug(title, description, member, priority, severity, stepsToReproduce);

        return String.format(CommandsConstants.BUG_CREATED_MESSAGE, createBug.getId());
    }

    private void parseParameters(List<String> parameters)
    {
        //First we will enter the name of the board where we want to add the bug
        boardName = parameters.get(0);

        //and then the bug fields for its initialization
        //TODO why don't we use parser helpers for the title? or other fields? I can input something else
        title = parameters.get(1);
        description = parameters.get(2);
        memberName = parameters.get(3);
        priority = ParsingHelpers.tryParseEnum(parameters.get(4), Priority.class, INVALID_PRIORITY);
        severity = ParsingHelpers.tryParseEnum(parameters.get(5), Severity.class, INVALID_SEVERITY);
        steps = parameters.get(6);
        stepsToReproduce = Arrays.stream(steps.split(";")).toList();



    }
}
