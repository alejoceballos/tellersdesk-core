package ac.td.core.action.task.skill;

import ac.td.core.character.AttributeType;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

@SkillMetadata(
        defaultApplicableSpecialties = { SpecialtyType.HACKING, SpecialtyType.DIGITAL_SECURITY },
        drivingSkill = SkillType.COMPUTER,
        drivingAttribute = AttributeType.INTELLIGENCE
)
public class HackIntoComputerSystemTest extends SkillTaskTest<HackIntoComputerSystem> {
}
