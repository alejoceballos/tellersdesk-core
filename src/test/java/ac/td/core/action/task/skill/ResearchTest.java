package ac.td.core.action.task.skill;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

import java.util.Set;

@SkillMetadata(
        type = ActionType.EXTENDED,
        categories = CategoryType.MENTAL,
        defaultApplicableSpecialties = SpecialtyType.RESEARCH,
        drivingSkill = SkillType.ACADEMICS,
        drivingAttribute = AttributeType.RESOLVE
)
public class ResearchTest extends SkillTaskTest<Research> {

    @Override
    protected Research creatTask(final SkillfulCharacter character, final DieFactory dieFactory) throws TaskException {
        return new Research(character, dieFactory);
    }

    @Override
    protected Research creatTask(
            final SkillfulCharacter character,
            final DieFactory dieFactory,
            final Set<SpecialtyType> nonDefaultApplicableSpecialties) throws TaskException {
        return new Research(character, dieFactory, nonDefaultApplicableSpecialties);
    }

}
