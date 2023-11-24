package taskmanagement.models.tasks.contracts;

public interface GenericStatus<T> {
    void changeStatus(T newStatus);

}
