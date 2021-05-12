package ac.td.core.action.task.skill;

import ac.td.core.character.AttributeType;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

@SkillMetadata(
        defaultApplicableSpecialties = SpecialtyType.RESEARCH,
        drivingSkill = SkillType.ACADEMICS,
        drivingAttribute = AttributeType.RESOLVE
)
public class ResearchTest extends SkillTaskTest<Research> {
}
