package taskmanagement.core;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.CommandFactory;
import taskmanagement.core.contracts.TaskManagementRepository;

public class CommandFactoryImpl implements CommandFactory
{
    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository) {
        return null;
    }
}
