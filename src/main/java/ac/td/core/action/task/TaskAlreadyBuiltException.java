package ac.td.core.action.task;

public class TaskAlreadyBuiltException extends TaskException {
    public TaskAlreadyBuiltException(final String message) {
        super(message);
    }
}
