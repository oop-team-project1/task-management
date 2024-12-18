package taskmanagement.commands.creation;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Member;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class CreateNewPerson implements Command
{
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementRepository taskManagementRepository;
    private String name;

    public CreateNewPerson(TaskManagementRepository taskManagementRepository)
    {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters)
    {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Member createPerson = taskManagementRepository.createNewPerson(name);

        return String.format(CommandsConstants.PERSON_CREATED_MESSAGE, createPerson.getName());
    }

    private void parseParameters(List<String> parameters)
    {
        name = parameters.get(0);
    }
}
