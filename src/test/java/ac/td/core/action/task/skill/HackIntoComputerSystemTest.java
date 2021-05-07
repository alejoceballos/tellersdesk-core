package ac.td.core.action.task.skill;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DiceRollException;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SkillMetadata(
        type = ActionType.EXTENDED,
        categories = CategoryType.MENTAL,
        defaultApplicableSpecialties = { SpecialtyType.HACKING, SpecialtyType.DIGITAL_SECURITY },
        drivingSkill = SkillType.COMPUTER,
        drivingAttribute = AttributeType.INTELLIGENCE
)
public class HackIntoComputerSystemTest extends SkillTaskTest<HackIntoComputerSystem> {

    @Override
    protected HackIntoComputerSystem creatTask(final SkillfulCharacter character, final DieFactory dieFactory)
            throws TaskException {
        return new HackIntoComputerSystem(character, dieFactory);
    }

    @Override
    protected HackIntoComputerSystem creatTask(
            final SkillfulCharacter character,
            final DieFactory dieFactory,
            final Set<SpecialtyType> nonDefaultApplicableSpecialties) throws TaskException {
        return new HackIntoComputerSystem(character, dieFactory, nonDefaultApplicableSpecialties);
    }
}
