package taskmanagement.commands.listing;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Member;

import java.util.List;

public class ShowAllPeople implements Command
{
    private TaskManagementRepository taskManagementRepository;

    public ShowAllPeople(TaskManagementRepository taskManagementRepository)
    {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters)
    {
        StringBuilder stringBuilder = new StringBuilder();
        List<Member> members = taskManagementRepository.getMembers();

        for (int i = 0; i < members.size(); i++)
        {
            stringBuilder.append(members.get(i).print());
        }

        return stringBuilder.toString().trim();
    }
}
