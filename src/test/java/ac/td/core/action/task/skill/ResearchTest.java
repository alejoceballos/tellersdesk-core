package ac.td.core.action.task.skill;

import ac.td.core.action.ActionType;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

@SkillMetadata(
        type = ActionType.EXTENDED,
        categories = CategoryType.MENTAL,
        defaultApplicableSpecialties = SpecialtyType.RESEARCH,
        drivingSkill = SkillType.ACADEMICS,
        drivingAttribute = AttributeType.RESOLVE
)
public class ResearchTest extends SkillTaskTest<Research> {
}
