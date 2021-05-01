package ac.td.core.action.task;

import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;

import java.util.Objects;

public class TaskFactory {
    private final SkillfulCharacter character;
    private final DieFactory dieFactory;

    public TaskFactory(final SkillfulCharacter character, final DieFactory dieFactory)
            throws TaskCharacterException, TaskDieFactoryException {

        if (Objects.isNull(character)) {
            throw new TaskCharacterException("Character cannot be null");
        }

        if (dieFactory == null) {
            throw new TaskDieFactoryException("Die factory cannot be null");
        }

        this.character = character;
        this.dieFactory = dieFactory;
    }
}
