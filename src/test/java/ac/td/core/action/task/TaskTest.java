package ac.td.core.action.task;

import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DiceRollException;
import ac.td.core.diceroll.Die;
import ac.td.core.diceroll.DieFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class TaskTest<T extends Task> {

    protected abstract T creatTask(final SkillfulCharacter character, final DieFactory dieFactory) throws TaskException;

    @Test
    public void new_NullCharacter() {
        Assertions.assertThrows(
                TaskCharacterException.class,
                () -> this.creatTask(null, Mockito.mock(DieFactory.class)));
    }

    @Test
    public void new_NullFactory() {
        Assertions.assertThrows(
                TaskDieFactoryException.class,
                () -> this.creatTask(Mockito.mock(SkillfulCharacter.class), null));
    }

    @Test
    public void new_Successful() throws TaskException {
        final Task task = this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class));
        Assertions.assertNull(task.getDiceRoll());
        Assertions.assertEquals(0, task.calculateModifiersDicePoolSize());
    }

    @Test
    public void setModifiersDicePoolSize_NullModifiers() {
        Assertions.assertThrows(
                TaskModifierException.class,
                () -> this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class))
                        .setModifiersDice((int[]) null));
    }

    @Test
    public void setModifiersDicePoolSize_Empty() {
        Assertions.assertThrows(
                TaskModifierException.class,
                () -> this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class))
                        .setModifiersDice(new int[0]));
    }

    @Test
    public void setModifiersDicePoolSize() throws TaskException {
        Assertions.assertEquals(
                3,
                this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class))
                        .setModifiersDice(0, 1, 2)
                        .calculateModifiersDicePoolSize());
    }

    @Test
    public abstract void perform() throws TaskException, DiceRollException;

    @Test
    public abstract void calculateDicePoolSize() throws TaskException;

    protected OngoingStubbing<Set<Die>> mockDiceSetResult(
            final DieFactory dieFactory,
            final List<Integer> expectedResult) {

        final Set<Die> dice = expectedResult.stream().map(value -> {
            final Die die = Mockito.mock(Die.class);
            Mockito.when(die.getLastResult()).thenReturn(value);
            return die;
        }).collect(Collectors.toSet());

        return Mockito.when(dieFactory.create(expectedResult.size())).thenReturn(dice);
    }

    @Test
    public abstract void getType() throws TaskException;

    @Test
    public abstract void getCategories() throws TaskException;

}
