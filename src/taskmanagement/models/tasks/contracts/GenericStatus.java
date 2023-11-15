package taskmanagement.models.tasks.contracts;

public interface GenericStatus<T> {
    public T getFinalStatus();
    public T getInitialStatus();
    public T[] getValues();
    public int getOrdinal();

}
