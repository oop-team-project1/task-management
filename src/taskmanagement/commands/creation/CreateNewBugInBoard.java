package taskmanagement.commands.creation;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.tasks.contracts.Bug;
import taskmanagement.models.tasks.enums.Priority;
import taskmanagement.models.tasks.enums.bug.Severity;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.Arrays;
import java.util.List;

public class CreateNewBugInBoard implements Command
{
    public static final int MAX_NUMBER_OF_ARGUMENTS = 7;
    public static final int MIN_NUMBER_OF_ARGUMENTS = 6;
    public static final String INVALID_PRIORITY = "Invalid priority type!";
    public static final String INVALID_SEVERITY = "Invalid severity type!";
    public static final String INVALID_ARGUMENTS_SIZE = "Invalid number of arguments!";
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
        ValidationHelper.validateValueInRange(parameters.size(), MIN_NUMBER_OF_ARGUMENTS, MAX_NUMBER_OF_ARGUMENTS, INVALID_ARGUMENTS_SIZE);
        int parametersCount = parameters.size();
        parseParameters(parameters, parametersCount);

        board = taskManagementRepository.findBoardByName(boardName);
        Bug createBug;
        if (parametersCount == MAX_NUMBER_OF_ARGUMENTS) {
            member = taskManagementRepository.findMemberByName(memberName);
            createBug = taskManagementRepository.createNewBugWithMember(title, description, member, priority, severity, stepsToReproduce);
            board.addTask(createBug, "Bug");
        }
        else
        {
            createBug = taskManagementRepository.createNewBugWithoutMember(title, description, priority, severity, stepsToReproduce);
            board.addTask(createBug, "Bug");
        }

        return String.format(CommandsConstants.BUG_CREATED_MESSAGE, createBug.getId());
    }

    private void parseParameters(List<String> parameters, int paramCount)
    {
        if (paramCount == MAX_NUMBER_OF_ARGUMENTS)
        {
            parseWithMember(parameters);
        }
        else
        {
            parseWithoutMember(parameters);
        }
    }

    private void parseWithMember(List<String> parameters)
    {
        boardName = parameters.get(0);
        title = parameters.get(1);
        description = parameters.get(2);
        memberName = parameters.get(3);
        priority = ParsingHelpers.tryParseEnum(parameters.get(4), Priority.class, INVALID_PRIORITY);
        severity = ParsingHelpers.tryParseEnum(parameters.get(5), Severity.class, INVALID_SEVERITY);
        steps = parameters.get(6);
        stepsToReproduce = Arrays.stream(steps.split(";")).toList();
    }
    private void parseWithoutMember(List<String> parameters)
    {
        boardName = parameters.get(0);
        title = parameters.get(1);
        description = parameters.get(2);
        priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class, INVALID_PRIORITY);
        severity = ParsingHelpers.tryParseEnum(parameters.get(4), Severity.class, INVALID_SEVERITY);
        steps = parameters.get(5);
        stepsToReproduce = Arrays.stream(steps.split(";")).toList();
    }
}
