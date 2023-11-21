package taskmanagement.core;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.CommandFactory;
import taskmanagement.core.contracts.Engine;
import taskmanagement.core.contracts.TaskManagementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagementEngineImpl implements Engine
{
    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";

    private final CommandFactory commandFactory;
    private final TaskManagementRepository taskManagementRepository;

    public TaskManagementEngineImpl()
    {
        commandFactory = new CommandFactoryImpl();
        taskManagementRepository = new TaskManagementRepositoryImpl();
    }

    @Override
    public void start()
    {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()) {
                    System.out.println(EMPTY_COMMAND_ERROR);
                    continue;
                }
                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(inputLine);
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    private void processCommand(String inputLine)
    {
        String commandName = extractCommandName(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName, taskManagementRepository);
        List<String> parameters = extractCommandParameters(inputLine);
        String executionResult = command.execute(parameters);
        System.out.println(executionResult);
    }

    private String extractCommandName(String inputLine)
    {
        return inputLine.split(" ")[0];
    }

    private List<String> extractCommandParameters(String inputLine)
    {
        String[] commandParts = inputLine.split(" --");
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }
}
