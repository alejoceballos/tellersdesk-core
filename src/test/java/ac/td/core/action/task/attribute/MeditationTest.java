package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;

import java.util.Set;

class MeditationTest extends AttributeTaskTest<Meditation> {

    @Override
    protected Meditation creatTask(SkillfulCharacter character, DieFactory dieFactory) throws TaskException {
        return new Meditation(character, dieFactory);
    }

    @Override
    public ActionType getAssertionType() {
        return ActionType.EXTENDED;
    }

    @Override
    public Set<CategoryType> getAssertionCategories() {
        return Set.of(CategoryType.MENTAL);
    }

    @Override
    protected AttributeType[] contextDrivingAttributes() {
        return new AttributeType[] { AttributeType.RESOLVE, AttributeType.COMPOSURE };
    }

}
