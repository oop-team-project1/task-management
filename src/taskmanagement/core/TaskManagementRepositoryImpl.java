package taskmanagement.core;

import taskmanagement.core.contracts.TaskManagementRepository;
import taskmanagement.models.contracts.Board;
import taskmanagement.models.contracts.Member;
import taskmanagement.models.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    private List<Team> teams;

    List<Member> members;
    List<Board> boards;
    private int Id;




    public TaskManagementRepositoryImpl(){
        teams = new ArrayList<>();
        Id = 0;
    }






}
