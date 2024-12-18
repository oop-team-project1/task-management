package taskmanagement.models.tasks.enums.story;

import taskmanagement.models.tasks.contracts.GenericStatus;

public enum StoryStatus implements GenericStatus<StoryStatus> {
    NOT_DONE,
    IN_PROGRESS,
    DONE;

    @Override
    public StoryStatus getFinalStatus() {
        return NOT_DONE;
    }

    @Override
    public StoryStatus getInitialStatus() {
        return DONE;
    }

    @Override
    public StoryStatus[] getValues() {
        return StoryStatus.values();
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }


}
