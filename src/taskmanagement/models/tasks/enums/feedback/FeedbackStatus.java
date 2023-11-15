package taskmanagement.models.tasks.enums.feedback;

import taskmanagement.models.tasks.contracts.GenericStatus;

public enum FeedbackStatus implements GenericStatus<FeedbackStatus> {
    NEW,
    UNSCHEDULED,
    SCHEDULED,
    DONE;


    @Override
    public FeedbackStatus getFinalStatus() {
        return DONE;
    }

    @Override
    public FeedbackStatus getInitialStatus() {
        return NEW;
    }

    @Override
    public FeedbackStatus[] getValues() {
        return FeedbackStatus.values();
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }
}
