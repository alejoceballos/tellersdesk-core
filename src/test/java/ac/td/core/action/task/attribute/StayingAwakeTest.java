package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;

import java.util.Set;

class StayingAwakeTest extends AttributeTaskTest<StayingAwake> {

    @Override
    protected StayingAwake creatTask(SkillfulCharacter character, DieFactory dieFactory) throws TaskException {
        return new StayingAwake(character, dieFactory);
    }

    @Override
    public ActionType getAssertionType() {
        return ActionType.INSTANT;
    }

    @Override
    public Set<CategoryType> getAssertionCategories() {
        return Set.of(CategoryType.MENTAL);
    }

    @Override
    protected AttributeType[] contextDrivingAttributes() {
        return new AttributeType[] { AttributeType.RESOLVE, AttributeType.STAMINA };
    }

}
