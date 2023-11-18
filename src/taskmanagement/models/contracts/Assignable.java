package taskmanagement.models.contracts;

public interface Assignable {
    Member getAssignee();
    void setAssignee(Member member);
    void removeAssignee(Member member);
}
