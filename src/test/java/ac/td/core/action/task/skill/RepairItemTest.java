package ac.td.core.action.task.skill;

import ac.td.core.character.AttributeType;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

@SkillMetadata(
        defaultApplicableSpecialties = {
                SpecialtyType.AUTOMOTIVE,
                SpecialtyType.CARPENTRY,
                SpecialtyType.SCULPTING,
                SpecialtyType.JURY_RIGGING,
                SpecialtyType.WELDING
        },
        drivingSkill = SkillType.CRAFTS,
        drivingAttribute = AttributeType.DEXTERITY
)
public class RepairItemTest extends SkillTaskTest<RepairItem> {
}
