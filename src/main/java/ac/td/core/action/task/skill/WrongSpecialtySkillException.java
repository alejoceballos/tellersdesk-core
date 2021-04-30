package ac.td.core.action.task.skill;

import ac.td.core.action.task.TaskException;

public class WrongSpecialtySkillException extends TaskException {
    public WrongSpecialtySkillException(final String message) {
        super(message);
    }
}
