package ac.td.core.action.task.skill;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskCharacterException;
import ac.td.core.action.task.TaskDieFactoryException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

import java.util.Set;

@SkillMetadata(
        type = ActionType.EXTENDED,
        defaultApplicableSpecialties = { SpecialtyType.DREAMS, SpecialtyType.CRIME_SCENES },
        drivingSkill = SkillType.INVESTIGATION,
        drivingAttribute = AttributeType.WITS
)
public class IdentifyPattern extends SkillTask {

    public IdentifyPattern(final SkillfulCharacter character, final DieFactory dieFactory)
            throws TaskCharacterException, TaskDieFactoryException {
        super(character, dieFactory);
    }

    public IdentifyPattern(
            final SkillfulCharacter character,
            final DieFactory dieFactory,
            final Set<SpecialtyType> nonDefaultApplicableSpecialties)
            throws TaskCharacterException, TaskDieFactoryException, WrongSpecialtySkillException {
        super(character, dieFactory, nonDefaultApplicableSpecialties);
    }

}
