package ac.td.core.action.task.attribute;

import ac.td.core.action.task.TaskException;
import ac.td.core.action.task.TaskTest;
import ac.td.core.character.AttributeType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DiceRollException;
import ac.td.core.diceroll.DieFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public abstract class AttributeTaskTest<T extends AttributeTask> extends TaskTest<T> {
    @Override
    @Test
    public void perform() throws TaskException, DiceRollException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(this.contextDrivingAttributes()[0])).thenReturn(1);
        Mockito.when(character.getAttribute(this.contextDrivingAttributes()[1])).thenReturn(2);
        final DieFactory dieFactory = Mockito.mock(DieFactory.class);
        final int modifiersDicePoolSIze = -1;

        final List<Integer> expectedResult = Arrays.asList(7, 5);
        this.mockDiceSetResult(dieFactory, expectedResult);

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
    @Test
    public void calculateDicePoolSize() throws TaskException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(contextDrivingAttributes()[0])).thenReturn(1);
        Mockito.when(character.getAttribute(contextDrivingAttributes()[1])).thenReturn(2);

        Assertions.assertEquals(
                0,
                this.creatTask(character, Mockito.mock(DieFactory.class))
                        .setModifiersDice(-1, -2)
                        .calculateDicePoolSize());
    }

    @Test
    public void getDrivingAttributes() throws TaskException {
        final T task = this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class));
        final Set<AttributeType> expected = Set.of(this.contextDrivingAttributes());

        Assertions.assertEquals(task.getDrivingAttributes().size(), expected.size());
        Assertions.assertTrue(task.getDrivingAttributes().containsAll(expected));
        Assertions.assertTrue(expected.containsAll(task.getDrivingAttributes()));
    }

    protected abstract AttributeType[] contextDrivingAttributes();

}
