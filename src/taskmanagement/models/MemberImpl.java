package taskmanagement.models;

import taskmanagement.models.tasks.TaskImpl;
import taskmanagement.utils.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl {

    public static final String NAME_LENGTH_ERR = "Member name must be between %s and %s characters long!";
    private String name;
    private List<TaskImpl> taskImpls;
    private List<String> activityHistory;

    public MemberImpl(String name) {
        setName(name);
        taskImpls = new ArrayList<>();
        activityHistory = new ArrayList<>();
    }

    private void setName(String name) {
        ValidationHelper.validateStringLength(name,
                                            TeamImpl.NAME_LEN_MIN,
                                            TeamImpl.NAME_LEN_MAX,
                                            String.format(NAME_LENGTH_ERR,
                                                    TeamImpl.NAME_LEN_MIN,
                                                    TeamImpl.NAME_LEN_MAX));
        this.name = name;
    }


}
