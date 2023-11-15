package taskmanagement.commands.creation;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;

import java.util.List;

public class CreateNewPerson implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private TaskManagementRepository taskManagementRepository;

    private  String name;

    @Override
    public String execute(List<String> parameters) {
        return "";
    }
}
