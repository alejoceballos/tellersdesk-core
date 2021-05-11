package ac.td.core.action.task.skill;

import ac.td.core.action.ActionType;
import ac.td.core.character.AttributeType;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

@SkillMetadata(
        type = ActionType.EXTENDED,
        defaultApplicableSpecialties = {
                SpecialtyType.AUTOMOTIVE,
                SpecialtyType.CARPENTRY,
                SpecialtyType.SCULPTING,
                SpecialtyType.WELDING
        },
        drivingSkill = SkillType.CRAFTS,
        drivingAttribute = AttributeType.WITS
)
public class LookForFlawsTest extends SkillTaskTest<LookForFlaws> {
}
