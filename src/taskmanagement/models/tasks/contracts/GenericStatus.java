package taskmanagement.models.tasks.contracts;

public interface GenericStatus<T> {

    public void changeStatus(T newStatus);

}
