package ac.td.core.action.task.attribute;

import ac.td.core.action.task.Task;
import ac.td.core.action.task.TaskCharacterException;
import ac.td.core.action.task.TaskDieFactoryException;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;

import java.util.Set;

public abstract class AttributeTask extends Task {

    private final AttributeMetadata attributeMetadata;

    public AttributeTask(SkillfulCharacter character, DieFactory dieFactory) throws TaskCharacterException, TaskDieFactoryException {
        super(character, dieFactory);
        this.attributeMetadata =  this.getClass().getAnnotation(AttributeMetadata.class);
    }

    @Override
    public int calculateDicePoolSize() {
        final int attributesDicePool = Set.of(this.attributeMetadata.drivingAttributes()).stream()
                .mapToInt(this.character::getAttribute)
                .sum();
        final int modifiers = this.calculateModifiersDicePoolSize();

        return attributesDicePool + modifiers;
    }

}
