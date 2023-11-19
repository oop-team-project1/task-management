package taskmanagement.commands.modifying;

import taskmanagement.commands.CommandsConstants;
import taskmanagement.commands.contracts.Command;
import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;
import taskmanagement.utils.ValidationHelper;

import java.util.List;

public class AddPersonToTeam implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementRepository taskManagementRepository;
    private String memberName;
    private String teamName;


    public AddPersonToTeam(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);

        Member member = taskManagementRepository.findMemberByName(memberName);
        Team team = taskManagementRepository.findTeamByName(teamName);

        team.addMember(member);

        return String.format(CommandsConstants.MEMBER_ADDED_TO_TEAM, memberName, teamName);
    }

    private void parseParameters(List<String> parameters) {
        memberName = parameters.get(0);
        teamName = parameters.get(1);


    }

}
