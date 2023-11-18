package taskmanagement.models.contracts;

public interface Assignable {
    public Member getAssignee();
    public void setAssignee(Member member);
    public void removeAssignee(Member member);
}
