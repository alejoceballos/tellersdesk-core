package ac.td.core.action.task.skill;

import ac.td.core.character.AttributeType;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

@SkillMetadata(
        defaultApplicableSpecialties = SpecialtyType.CRIME_SCENES,
        drivingSkill = SkillType.INVESTIGATION,
        drivingAttribute = AttributeType.WITS
)
public class ExamineCrimeSceneTest extends SkillTaskTest<ExamineCrimeScene> {
}
