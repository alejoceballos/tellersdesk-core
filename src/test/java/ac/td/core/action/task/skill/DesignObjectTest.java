package ac.td.core.action.task.skill;

import ac.td.core.character.AttributeType;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

@SkillMetadata(
        defaultApplicableSpecialties = {
                SpecialtyType.AUTOMOTIVE,
                SpecialtyType.CARPENTRY,
                SpecialtyType.SCULPTING,
                SpecialtyType.WELDING
        },
        drivingSkill = SkillType.CRAFTS,
        drivingAttribute = AttributeType.INTELLIGENCE
)
public class DesignObjectTest extends SkillTaskTest<DesignObject> {
}
