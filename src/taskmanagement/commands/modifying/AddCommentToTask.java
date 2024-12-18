package taskmanagement.commands.modifying;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.comment.Comment;
import taskmanagement.models.comment.CommentImpl;
import taskmanagement.models.tasks.contracts.Task;
import taskmanagement.utils.ParsingHelpers;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class AddCommentToTask implements Command
{
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private final TaskManagementRepository taskManagementRepository;
    private int taskId;
    private String content;
    private String author;


    public AddCommentToTask(TaskManagementRepository taskManagementRepository)
    {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters)
    {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Task task = taskManagementRepository.findTaskById(taskId);
        Comment comment = new CommentImpl(content,author);

        task.addComment(comment);

        return String.format(CommandsConstants.COMMENT_ADDED_TO_TASK, task.getId());
    }

    private void parseParameters(List<String> parameters)
    {
        taskId = ParsingHelpers.tryParseInteger(parameters.get(0), "task id");
        content = parameters.get(1);
        author = parameters.get(2);

    }
}
