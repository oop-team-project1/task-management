package taskmanagement.commands.listing;

import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Member;
import taskmanagement.utils.ListingHelpers;

import java.util.List;

public class ShowAllPeople implements Command
{
    private static final String MEMBER_ERROR_MESSAGE = "There are no registered members!";
    private final List<Member> members;

    public ShowAllPeople(TaskManagementRepository taskManagementRepository)
    {
        members = taskManagementRepository.getMembers();
    }

    @Override
    public String execute(List<String> parameters)
    {
        if (members.isEmpty())
        {
            return MEMBER_ERROR_MESSAGE;
        }

        return ListingHelpers.elementsToString(members);
    }
}
