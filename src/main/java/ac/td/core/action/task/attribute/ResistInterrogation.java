package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskCharacterException;
import ac.td.core.action.task.TaskDieFactoryException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;

@AttributeMetadata(
        drivingAttributes = { AttributeType.STAMINA, AttributeType.RESOLVE },
        categories = CategoryType.PHYSICAL,
        type = ActionType.REFLEXIVE
)
public class ResistInterrogation extends AttributeTask {

    public ResistInterrogation(SkillfulCharacter character, DieFactory dieFactory)
            throws TaskCharacterException, TaskDieFactoryException {
        super(character, dieFactory);
    }

}
