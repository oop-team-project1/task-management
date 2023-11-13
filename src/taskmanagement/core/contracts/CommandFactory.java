package taskmanagement.core.contracts;

import taskmanagement.commands.contracts.Command;

public interface CommandFactory
{
    Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository);
}
