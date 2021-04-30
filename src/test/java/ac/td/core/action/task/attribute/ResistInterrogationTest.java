package ac.td.core.action.task.attribute;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;

import java.util.Set;

class ResistInterrogationTest extends AttributeTaskTest<ResistInterrogation> {

    @Override
    protected ResistInterrogation creatTask(SkillfulCharacter character, DieFactory dieFactory) throws TaskException {
        return new ResistInterrogation(character, dieFactory);
    }

    @Override
    public ActionType getAssertionType() {
        return ActionType.REFLEXIVE;
    }

    @Override
    public Set<CategoryType> getAssertionCategories() {
        return Set.of(CategoryType.PHYSICAL);
    }

    @Override
    protected AttributeType[] contextDrivingAttributes() {
        return new AttributeType[] { AttributeType.STAMINA, AttributeType.RESOLVE };
    }
}
