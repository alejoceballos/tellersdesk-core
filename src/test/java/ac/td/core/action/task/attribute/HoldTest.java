package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;

import java.util.Set;

class HoldTest extends AttributeTaskTest<Hold> {

    @Override
    protected Hold creatTask(SkillfulCharacter character, DieFactory dieFactory) throws TaskException {
        return new Hold(character, dieFactory);
    }

    @Override
    protected AttributeType[] contextDrivingAttributes() {
        return new AttributeType[] { AttributeType.STRENGTH, AttributeType.STAMINA };
    }

    @Override
    public ActionType getAssertionType() {
        return ActionType.EXTENDED;
    }

    @Override
    public Set<CategoryType> getAssertionCategories() {
        return Set.of(CategoryType.PHYSICAL);
    }

}
