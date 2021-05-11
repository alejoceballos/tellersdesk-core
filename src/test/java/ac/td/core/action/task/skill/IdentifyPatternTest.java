package ac.td.core.action.task.skill;

import ac.td.core.action.ActionType;
import ac.td.core.character.AttributeType;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

@SkillMetadata(
        type = ActionType.EXTENDED,
        defaultApplicableSpecialties = { SpecialtyType.DREAMS, SpecialtyType.CRIME_SCENES },
        drivingSkill = SkillType.INVESTIGATION,
        drivingAttribute = AttributeType.WITS
)
public class IdentifyPatternTest extends SkillTaskTest<IdentifyPattern> {
}
