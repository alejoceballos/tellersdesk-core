package ac.td.core.action.task.skill;

import ac.td.core.action.task.Task;
import ac.td.core.action.task.TaskException;
import ac.td.core.action.task.TaskTest;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DiceRollException;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SpecialtyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class SkillTaskTest<T extends SkillTask> extends TaskTest<T> {

    // NOTE 1: calculateDicePoolSize takes the default part of "With Skill Familiarity And Without Specialty"

    // NOTE 2: Character's cannot have a specialty for an unfamiliar skill, so there is no sense on implementing
    // "Without Skill Familiarity And With Specialty"

    public SkillMetadata skillTestMetadata;

    @BeforeEach
    public void before() {
        this.skillTestMetadata = this.getClass().getAnnotation(SkillMetadata.class);
    }

    @Override
    protected T creatTask(
            final SkillfulCharacter character,
            final DieFactory dieFactory)
            throws TaskException {
        try {
            return this.getSubjectClass().getConstructor(SkillfulCharacter.class, DieFactory.class)
                    .newInstance(character, dieFactory);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            if (e.getCause() != null && e.getCause() instanceof TaskException) {
                throw (TaskException) e.getCause();
            }

            throw new RuntimeException(e);
        }
    }

    protected T creatTask(
            final SkillfulCharacter character,
            final DieFactory dieFactory,
            final Set<SpecialtyType> nonDefaultApplicableSpecialties)
            throws TaskException {
        try {
            return this.getSubjectClass().getConstructor(SkillfulCharacter.class, DieFactory.class, Set.class)
                    .newInstance(character, dieFactory, nonDefaultApplicableSpecialties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            if (e.getCause() != null && e.getCause() instanceof TaskException) {
                throw (TaskException) e.getCause();
            }

            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<T> getSubjectClass() {
        final ParameterizedType parameterizedType = ((ParameterizedType) this.getClass().getGenericSuperclass());
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

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
                        Set.of(SpecialtyType.NOT_APPLICABLE)));
    }

    @Test
    public void getApplicableSpecialties() throws TaskException {
        final SkillTask task = this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class));
        final SkillMetadata metadata = task.getClass().getAnnotation(SkillMetadata.class);

        Assertions.assertEquals(this.skillTestMetadata.defaultApplicableSpecialties().length, metadata.defaultApplicableSpecialties().length);
        Assertions.assertTrue(Set.of(this.skillTestMetadata.defaultApplicableSpecialties()).containsAll(Set.of(metadata.defaultApplicableSpecialties())));
        Assertions.assertTrue(Set.of(metadata.defaultApplicableSpecialties()).containsAll(Set.of(this.skillTestMetadata.defaultApplicableSpecialties())));
    }

    @Test
    public void getDrivingSkill() throws TaskException {
        final SkillTask task = this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class));
        final SkillMetadata metadata = task.getClass().getAnnotation(SkillMetadata.class);

        Assertions.assertEquals(this.skillTestMetadata.drivingSkill(), metadata.drivingSkill());
    }

    @Test
    @Override
    public void getType() throws TaskException {
        final Task task = this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class));
        final SkillMetadata metadata = task.getClass().getAnnotation(SkillMetadata.class);

        Assertions.assertEquals(this.skillTestMetadata.type(), metadata.type());
    }

    @Test
    @Override
    public void getCategories() throws TaskException {
        final Task task = this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class));
        final SkillMetadata metadata = task.getClass().getAnnotation(SkillMetadata.class);
        Assertions.assertEquals(this.skillTestMetadata.categories().length, metadata.categories().length);
        Assertions.assertTrue(Set.of(this.skillTestMetadata.categories()).containsAll(Set.of(metadata.categories())));
        Assertions.assertTrue(Set.of(metadata.categories()).containsAll(Set.of(this.skillTestMetadata.categories())));
    }

    @Override
    @Test
    public void calculateDicePoolSize() throws TaskException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(this.skillTestMetadata.drivingAttribute())).thenReturn(1);
        Mockito.when(character.getSkill(this.skillTestMetadata.drivingSkill())).thenReturn(2);

        Assertions.assertEquals(
                0,
                this.creatTask(character, Mockito.mock(DieFactory.class))
                        .setModifiersDice(-1, -2)
                        .calculateDicePoolSize());
    }

    @Test
    public void calculateDicePoolSize_WithSkillFamiliarityAndWithSpecialty() throws TaskException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(this.skillTestMetadata.drivingAttribute())).thenReturn(1);
        Mockito.when(character.getSkill(this.skillTestMetadata.drivingSkill())).thenReturn(2);
        Mockito.when(character.getSpecialties()).thenReturn(Set.of(this.skillTestMetadata.defaultApplicableSpecialties())); // +1

        Assertions.assertEquals(
                1,
                this.creatTask(character, Mockito.mock(DieFactory.class))
                        .setModifiersDice(-1, -2)
                        .calculateDicePoolSize());
    }

    @Test
    public void calculateDicePoolSize_WithoutSkillFamiliarity() throws TaskException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(this.skillTestMetadata.drivingAttribute())).thenReturn(4);
        Mockito.when(character.getSkill(this.skillTestMetadata.drivingSkill())).thenReturn(0); // MENTAL: -3

        Assertions.assertEquals(
                0,
                this.creatTask(character, Mockito.mock(DieFactory.class))
                        .setModifiersDice(-1)
                        .calculateDicePoolSize());
    }

    @Test
    public void perform() throws TaskException, DiceRollException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(this.skillTestMetadata.drivingAttribute())).thenReturn(1);
        Mockito.when(character.getSkill(this.skillTestMetadata.drivingSkill())).thenReturn(2);

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

}
