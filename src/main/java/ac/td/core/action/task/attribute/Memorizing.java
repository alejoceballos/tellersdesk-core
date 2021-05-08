package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskCharacterException;
import ac.td.core.action.task.TaskDieFactoryException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;

@AttributeMetadata(
        drivingAttributes = { AttributeType.INTELLIGENCE, AttributeType.COMPOSURE },
        categories = CategoryType.MENTAL,
        type = ActionType.INSTANT
)
public class Memorizing extends AttributeTask {

    public Memorizing(SkillfulCharacter character, DieFactory dieFactory)
            throws TaskCharacterException, TaskDieFactoryException {
        super(character, dieFactory);
    }

}
