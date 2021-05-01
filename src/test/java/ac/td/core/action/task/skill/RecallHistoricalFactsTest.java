package ac.td.core.action.task.skill;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskException;
import ac.td.core.action.task.skill.RecallHistoricalFacts;
import ac.td.core.action.task.skill.SkillTaskTest;
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

public class RecallHistoricalFactsTest extends SkillTaskTest<RecallHistoricalFacts> {

    @Override
    protected RecallHistoricalFacts creatTask(final SkillfulCharacter character, final DieFactory dieFactory) throws TaskException {
        return new RecallHistoricalFacts(character, dieFactory);
    }

    @Override
    protected RecallHistoricalFacts creatTask(
            final SkillfulCharacter character,
            final DieFactory dieFactory,
            final Set<SpecialtyType> nonDefaultApplicableSpecialties) throws TaskException {
        return new RecallHistoricalFacts(character, dieFactory, nonDefaultApplicableSpecialties);
    }

    @Override
    @Test
    public void calculateDicePoolSize() throws TaskException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(AttributeType.INTELLIGENCE)).thenReturn(1);
        Mockito.when(character.getSkill(SkillType.ACADEMICS)).thenReturn(2);

        Assertions.assertEquals(
                0,
                this.creatTask(character, Mockito.mock(DieFactory.class))
                        .setModifiersDice(-1, -2)
                        .calculateDicePoolSize());
    }

    @Override
    @Test
    public void calculateDicePoolSize_WithSkillFamiliarityAndWithSpecialty() throws TaskException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(AttributeType.INTELLIGENCE)).thenReturn(1);
        Mockito.when(character.getSkill(SkillType.ACADEMICS)).thenReturn(2);
        Mockito.when(character.getSpecialties()).thenReturn(Set.of(SpecialtyType.HISTORY)); // +1

        Assertions.assertEquals(
                1,
                this.creatTask(character, Mockito.mock(DieFactory.class))
                        .setModifiersDice(-1, -2)
                        .calculateDicePoolSize());
    }

    @Override
    @Test
    public void calculateDicePoolSize_WithoutSkillFamiliarity() throws TaskException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(AttributeType.INTELLIGENCE)).thenReturn(4);
        Mockito.when(character.getSkill(SkillType.ACADEMICS)).thenReturn(0); // MENTAL: -3

        Assertions.assertEquals(
                0,
                this.creatTask(character, Mockito.mock(DieFactory.class))
                        .setModifiersDice(-1)
                        .calculateDicePoolSize());
    }

    @Override
    public Set<SpecialtyType> getAssertionApplicableSpecialties() {
        return Set.of(SpecialtyType.HISTORY);
    }

    @Override
    public SkillType getAssertionDrivingSkill() {
        return SkillType.ACADEMICS;
    }

    @Override
    @Test
    public void perform() throws TaskException, DiceRollException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(Mockito.any())).thenReturn(1);
        Mockito.when(character.getSkill(SkillType.ACADEMICS)).thenReturn(2);

        final DieFactory dieFactory = Mockito.mock(DieFactory.class);
        final List<Integer> expectedResult = Arrays.asList(6, 7);
        this.mockDiceSetResult(dieFactory, expectedResult);

        final int modifiersDicePoolSIze = -1;

        final List<Integer> actualResult = this.creatTask(character, dieFactory)
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
        return Set.of(CategoryType.MENTAL);
    }

}
