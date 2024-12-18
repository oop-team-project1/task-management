package taskmanagement.commands.creation;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;

import java.util.List;

public class CreateNewPerson implements Command
{
    private TaskManagementRepository taskManagementRepository;

    @Override
    public String execute(List<String> parameters)
    {
        return "";
    }
}
