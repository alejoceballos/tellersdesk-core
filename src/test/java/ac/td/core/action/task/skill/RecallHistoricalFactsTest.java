package ac.td.core.action.task.skill;

import ac.td.core.character.AttributeType;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

@SkillMetadata(
        defaultApplicableSpecialties = SpecialtyType.HISTORY,
        drivingSkill = SkillType.ACADEMICS,
        drivingAttribute = AttributeType.INTELLIGENCE
)
public class RecallHistoricalFactsTest extends SkillTaskTest<RecallHistoricalFacts> {
}
