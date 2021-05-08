package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskCharacterException;
import ac.td.core.action.task.TaskDieFactoryException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;

@AttributeMetadata(
        drivingAttributes = { AttributeType.WITS, AttributeType.DEXTERITY },
        categories = CategoryType.MENTAL,
        type = ActionType.INSTANT
)
public class CatchingDroppedObject extends AttributeTask {

    public CatchingDroppedObject(SkillfulCharacter character, DieFactory dieFactory)
            throws TaskCharacterException, TaskDieFactoryException {
        super(character, dieFactory);
    }

}
