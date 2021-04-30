package ac.td.core.action.task;

import ac.td.core.TellersDeskCoreException;

public class TaskException extends TellersDeskCoreException {
    public TaskException(Throwable cause) {
        super(cause);
    }
    public TaskException(final String message) {
        super(message);
    }
}
