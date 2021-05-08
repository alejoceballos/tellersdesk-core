package ac.td.core.action.task.skill;

import ac.td.core.action.ActionType;
import ac.td.core.character.AttributeType;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

@SkillMetadata(
        type = ActionType.INSTANT,
        defaultApplicableSpecialties = SpecialtyType.HISTORY,
        drivingSkill = SkillType.ACADEMICS,
        drivingAttribute = AttributeType.INTELLIGENCE
)
public class RecallHistoricalFactsTest extends SkillTaskTest<RecallHistoricalFacts> {
}
