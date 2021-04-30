package ac.td.core.action.task.attribute;

import ac.td.core.action.task.Task;
import ac.td.core.action.task.TaskCharacterException;
import ac.td.core.action.task.TaskDieFactoryException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;

import java.util.Set;

public abstract class AttributeTask extends Task {

    public AttributeTask(SkillfulCharacter character, DieFactory dieFactory) throws TaskCharacterException, TaskDieFactoryException {
        super(character, dieFactory);
    }

    @Override
    public int calculateBasicPoolSize() {
        final int attributesDicePool = this.getDrivingAttributes().stream()
                .mapToInt(this.character::getAttribute)
                .sum();
        final int modifiers = this.calculateModifiersDicePoolSize();

        return attributesDicePool + modifiers;
    }

    public abstract Set<AttributeType> getDrivingAttributes();

}
