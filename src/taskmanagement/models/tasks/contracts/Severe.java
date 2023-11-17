package taskmanagement.models.tasks.contracts;

import taskmanagement.models.tasks.enums.bug.Severity;

public interface Severe {
    public Severity getSeverity();
    public void changeSeverity(Severity newSeverity);
}
