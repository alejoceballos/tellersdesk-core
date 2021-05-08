package ac.td.core.action.task.attribute;

import ac.td.core.action.task.TaskException;
import ac.td.core.action.task.TaskTest;
import ac.td.core.character.AttributeType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DiceRollException;
import ac.td.core.diceroll.DieFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public abstract class AttributeTaskTest<T extends AttributeTask> extends TaskTest<T> {

    public AttributeMetadata attributeTestMetadata;

    @BeforeEach
    public void before() {
        this.attributeTestMetadata = this.getClass().getAnnotation(AttributeMetadata.class);
    }

    @Override
    @Test
    public void perform() throws TaskException, DiceRollException {
        final SkillfulCharacter character = Mockito.mock(SkillfulCharacter.class);
        Mockito.when(character.getAttribute(this.attributeTestMetadata.drivingAttributes()[0])).thenReturn(1);
        Mockito.when(character.getAttribute(this.attributeTestMetadata.drivingAttributes()[1])).thenReturn(2);
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
        Mockito.when(character.getAttribute(this.attributeTestMetadata.drivingAttributes()[0])).thenReturn(1);
        Mockito.when(character.getAttribute(this.attributeTestMetadata.drivingAttributes()[1])).thenReturn(2);

        Assertions.assertEquals(
                0,
                this.creatTask(character, Mockito.mock(DieFactory.class))
                        .setModifiersDice(-1, -2)
                        .calculateDicePoolSize());
    }

    @Test
    public void getDrivingAttributes() throws TaskException {
        final T task = this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class));
        final Set<AttributeType> expected = Set.of(this.attributeTestMetadata.drivingAttributes());
        final AttributeMetadata metadata = task.getClass().getAnnotation(AttributeMetadata.class);

        Assertions.assertEquals(metadata.drivingAttributes().length, expected.size());
        Assertions.assertTrue(Set.of(metadata.drivingAttributes()).containsAll(expected));
        Assertions.assertTrue(expected.containsAll(Set.of(metadata.drivingAttributes())));
    }

    @Test
    public void getType() throws TaskException {
        final AttributeTask task = this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class));
        final AttributeMetadata metadata = task.getClass().getAnnotation(AttributeMetadata.class);
        Assertions.assertEquals(this.attributeTestMetadata.type(), metadata.type());
    }

    @Test
    public void getCategories() throws TaskException {
        final AttributeTask task = this.creatTask(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class));
        final AttributeMetadata metadata = task.getClass().getAnnotation(AttributeMetadata.class);
        Assertions.assertEquals(this.attributeTestMetadata.categories().length, metadata.categories().length);
        Assertions.assertTrue(Set.of(this.attributeTestMetadata.categories()).containsAll(Set.of(metadata.categories())));
        Assertions.assertTrue(Set.of(metadata.categories()).containsAll(Set.of(this.attributeTestMetadata.categories())));
    }

}
