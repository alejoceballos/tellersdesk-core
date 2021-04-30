package ac.td.core.action.task.skill;

import ac.td.core.action.task.TaskException;
import ac.td.core.action.task.TaskTest;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

public abstract class SkillTaskTest<T extends SkillTask> extends TaskTest<T> {

    // NOTE 1: calculateDicePoolSize takes the default part of "With Skill Familiarity And Without Specialty"

    // NOTE 2: Character's cannot have a specialty for an unfamiliar skill, so there is no sense on implementing
    // "Without Skill Familiarity And With Specialty"

    protected abstract T creatTask(
            final SkillfulCharacter character,
            final DieFactory dieFactory,
            final Set<SpecialtyType> nonDefaultApplicableSpecialties)
            throws TaskException;

    @Test
    public void new_NullSpecialties() throws TaskException {
        Assertions.assertNotNull(
                this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class), null));
    }

    @Test
    public void new_EmptySpecialties() throws TaskException {
        Assertions.assertNotNull(
                this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class), new HashSet<>()));
    }

    @Test
    public void new_NonDrivingSkillRelatedSpecialties() {
        Assertions.assertThrows(
                WrongSpecialtySkillException.class,
                () -> this.creatTask(
                        Mockito.mock(SkillfulCharacter.class),
                        Mockito.mock(DieFactory.class),
                        Set.of(SpecialtyType.DUMMY_1)));
    }

    @Test
    public abstract void calculateDicePoolSize_WithSkillFamiliarityAndWithSpecialty() throws TaskException;

    @Test
    public abstract void calculateDicePoolSize_WithoutSkillFamiliarity() throws TaskException;

    @Test
    public void getApplicableSpecialties() throws TaskException {
        final SkillTask task = this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class));
        Assertions.assertEquals(this.getAssertionApplicableSpecialties().size(), task.getDefaultApplicableSpecialties().size());
        Assertions.assertTrue(this.getAssertionApplicableSpecialties().containsAll(task.getDefaultApplicableSpecialties()));
        Assertions.assertTrue(task.getDefaultApplicableSpecialties().containsAll(this.getAssertionApplicableSpecialties()));
    }

    public abstract Set<SpecialtyType> getAssertionApplicableSpecialties();

    @Test
    public void getDrivingSkill() throws TaskException {
        final SkillTask task = this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class));
        Assertions.assertEquals(this.getAssertionDrivingSkill(), task.getDrivingSkill());
    }

    public abstract SkillType getAssertionDrivingSkill();

}
