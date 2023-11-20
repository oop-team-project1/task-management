package taskmanagement.models.tasks.contracts;

public interface LinearStatusModification {
    void advanceStatus();
    void revertStatus();
    String currentStatus();
    final static String STATUS_DUPLICATION_ERR = "Status already set to %s!";
}
