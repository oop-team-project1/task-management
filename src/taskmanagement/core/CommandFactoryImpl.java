package taskmanagement.core;

import taskmanagement.commands.changing.*;
import taskmanagement.commands.contracts.Command;
import taskmanagement.commands.creation.*;
import taskmanagement.commands.enums.CommandType;
import taskmanagement.commands.listing.*;
import taskmanagement.commands.listing.sorting.*;
import taskmanagement.commands.modifying.*;
import taskmanagement.core.contracts.CommandFactory;
import taskmanagement.core.contracts.TaskManagementRepository;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommandFromCommandName(String commandName, TaskManagementRepository repository) {
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        switch (commandType) {
            case SHOWALLPEOPLE:
                return new ShowAllPeople(repository);
            case SHOWPERSONACTIVITY:
                return new ShowPersonActivity(repository);
            case SHOWTEAMACTIVITY:
                return new ShowTeamActivity(repository);
            case SHOWBOARDACTIVITY:
                return new ShowBoardActivity(repository);
            case CREATENEWPERSON:
                return new CreateNewPerson(repository);
            case CREATENEWTEAM:
                return new CreateNewTeam(repository);
            case SHOWALLTEAMS:
                return new ShowAllTeams(repository);
            case ADDPERSONTOTEAM:
                return new AddPersonToTeam(repository);
            case SHOWALLTEAMMEMBERS:
                return new ShowTeamMembers(repository);
            case CREATENEWBUG:
            case CREATENEWASSIGNEDBUG:
                return new CreateNewBugInBoard(repository);
            case CREATENEWSTORY:
            case CREATENEWASSIGNEDSTORY:
                return new CreateStory(repository);
            case CREATENEWFEEDBACK:
                return new CreateFeedbackInBoard(repository);
            case CHANGEBUGSTATUS:
                return new ChangeBugStatus(repository);
            case CHANGEBUGSEVERITY:
                return new ChangeBugSeverity(repository);
            case CHANGEBUGPRIORITY:
                return new ChangeBugPriority(repository);
            case CHANGESTORYSTATUS:
                return new ChangeStoryStatus(repository);
            case CHANGESTORYSIZE:
                return new ChangeSizeOfStory(repository);
            case CHANGESTORYPRIORITY:
                return new ChangePriorityOfStory(repository);
            case CHANGEFEEDBACKSTATUS:
                return new ChangeFeedbackStatus(repository);
            case CHANGEFEEDBACKRATING:
                return new ChangeFeedbackRating(repository);
            case CREATEBOARD:
                return new CreateBoardInATeam(repository);
            case ADDCOMMENTTOTASK:
                new AddCommentToTask(repository);
            case ASSIGNBUG:
                new AssignBug(repository);
            case UNASSIGNBUG:
                new UnassignBug(repository);
            case ASSIGNSTORY:
                new AssignStory(repository);
            case UNASSIGNSTORY:
                new UnassignStory(repository);
            case LISTALLTASKS:
                return null;
            case LISTBUGS:
                return null;
            case LISTSTORIES:
                return null;
            case LISTFEEDBACKS:
                return null;
            case LISTTASKWITHASSIGNEE:
                return null;
            case SORTBUGSBYTITLE:
                new SortBugsByTitle(repository);
            case SORTFEEDBACKSBYTITLE:
                new SortFeedbacksByTitle(repository);
            case SORTSTORIESBYTITLE:
                new SortStoriesByTitle(repository);
            case SORTBUGSBYPRIORITY:
                new SortBugsByPriority(repository);
            case SORTSTORIESBYPRIORITY:
                new SortStoriesByPriority(repository);
            case SORTBUGSBYSEVERITY:
                new SortBugsBySeverity(repository);
            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
        }

    }
}



