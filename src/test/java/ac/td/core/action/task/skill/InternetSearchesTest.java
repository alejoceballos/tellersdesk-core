package ac.td.core.action.task.skill;

import ac.td.core.character.AttributeType;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

@SkillMetadata(
        defaultApplicableSpecialties = { SpecialtyType.DATA_RETRIEVAL },
        drivingSkill = SkillType.COMPUTER,
        drivingAttribute = AttributeType.WITS
)
public class InternetSearchesTest extends SkillTaskTest<InternetSearches> {
}
