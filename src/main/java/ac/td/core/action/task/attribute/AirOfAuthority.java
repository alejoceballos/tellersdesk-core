package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskCharacterException;
import ac.td.core.action.task.TaskDieFactoryException;
import ac.td.core.action.task.attribute.AttributeTask;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;

import java.util.Set;

public class AirOfAuthority extends AttributeTask {

    public AirOfAuthority(SkillfulCharacter character, DieFactory dieFactory)
            throws TaskCharacterException, TaskDieFactoryException {
        super(character, dieFactory);
    }

    @Override
    public Set<AttributeType> getDrivingAttributes() {
        return Set.of(AttributeType.PRESENCE, AttributeType.INTELLIGENCE);
    }

    @Override
    public ActionType getType() {
        return ActionType.INSTANT;
    }

    @Override
    public Set<CategoryType> getCategories() {
        return Set.of(CategoryType.SOCIAL);
    }
}
