package taskmanagement.models.tasks.contracts;

import taskmanagement.models.tasks.enums.bug.Severity;

public interface Severe {
    Severity getSeverity();
    void changeSeverity(Severity newSeverity);
}
