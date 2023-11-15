package taskmanagement.models.tasks.enums.bug;

import taskmanagement.models.tasks.contracts.GenericStatus;

public enum BugStatus implements GenericStatus<BugStatus> {
    ACTIVE,
    DONE;

    @Override
    public BugStatus getFinalStatus() {
        return DONE;
    }

    @Override
    public BugStatus getInitialStatus() {
        return ACTIVE;
    }

    @Override
    public BugStatus[] getValues() {
        return BugStatus.values();
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }
}


