package ac.td.core.action.task.skill.drive;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskException;
import ac.td.core.action.task.skill.SkillTaskTest;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.skill.SkillType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DiceRollException;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SpecialtyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

class DriveTest extends SkillTaskTest<Drive> {

    @Override
    protected Drive creatTask(final SkillfulCharacter character, final DieFactory dieFactory) throws TaskException {
        return this.creatTask(character, dieFactory, VehicleModelType.COMPACT_CAR);
    }

    private Drive creatTask(final SkillfulCharacter character, final DieFactory dieFactory, final VehicleModelType vehicle)
            throws TaskException {
        return new Drive(character, dieFactory, vehicle);
    }

    @Override
    protected Drive creatTask(SkillfulCharacter character, DieFactory dieFactory, Set<SpecialtyType> nonDefaultApplicableSpecialties) throws TaskException {
        return new Drive(character, dieFactory, VehicleModelType.COMPACT_CAR, nonDefaultApplicableSpecialties);
    }

    @Override
    @Test
    public void calculateDicePoolSize() throws TaskException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(AttributeType.DEXTERITY)).thenReturn(1);
        Mockito.when(character.getSkill(SkillType.DRIVE)).thenReturn(2);

        final VehicleModelType vehicle = VehicleModelType.COMPACT_CAR; // +3

        Assertions.assertEquals(
                0,
                this.creatTask(character, Mockito.mock(DieFactory.class), vehicle)
                        .setModifiersDice(-1, -2, -3)
                        .calculateDicePoolSize());
    }

    @Override
    public void calculateDicePoolSize_WithSkillFamiliarityAndWithSpecialty() throws TaskException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(AttributeType.DEXTERITY)).thenReturn(1);
        Mockito.when(character.getSkill(SkillType.DRIVE)).thenReturn(2);

        final VehicleModelType vehicle = VehicleModelType.COMPACT_CAR; // +3

        Assertions.assertEquals(
                0,
                this.creatTask(character, Mockito.mock(DieFactory.class), vehicle)
                        .setModifiersDice(-1, -2, -3)
                        .calculateDicePoolSize());
    }

    @Override
    public void calculateDicePoolSize_WithoutSkillFamiliarity() throws TaskException {
        throw new RuntimeException("Not implemented");
    }

    @Override
    @Test
    public void perform() throws TaskException, DiceRollException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(Mockito.any())).thenReturn(1);
        Mockito.when(character.getSkill(SkillType.DRIVE)).thenReturn(2);
        final VehicleModelType vehicle = VehicleModelType.COMPACT_CAR; // +3
        final DieFactory dieFactory = Mockito.mock(DieFactory.class);
        final int modifiersDicePoolSIze = -1;

        final List<Integer> expectedResult = Arrays.asList(9, 8, 7, 6, 5);
        this.mockDiceSetResult(dieFactory, expectedResult);

        final List<Integer> actualResult = this.creatTask(character, dieFactory, vehicle)
                .setModifiersDice(modifiersDicePoolSIze)
                .perform()
                .getDiceRoll()
                .result();

        Assertions.assertEquals(expectedResult.size(), actualResult.size());
        Assertions.assertTrue(expectedResult.containsAll(actualResult));
        Assertions.assertTrue(actualResult.containsAll(expectedResult));
    }

    @Override
    public ActionType getAssertionType() {
        return ActionType.INSTANT;
    }

    @Override
    public Set<CategoryType> getAssertionCategories() {
        return Set.of(CategoryType.PHYSICAL);
    }

    @Override
    public Set<SpecialtyType> getAssertionApplicableSpecialties() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public SkillType getAssertionDrivingSkill() {
        throw new RuntimeException("Not implemented");
    }
}
