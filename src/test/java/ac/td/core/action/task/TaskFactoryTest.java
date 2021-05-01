package ac.td.core.action.task;

import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TaskFactoryTest {

    @Test
    public void new_CharacterNull() {
        Assertions.assertThrows(
                TaskCharacterException.class,
                () -> new TaskFactory(null, Mockito.mock(DieFactory.class)));
    }

    @Test
    public void new_DieFactoryNull() {
        Assertions.assertThrows(
                TaskDieFactoryException.class,
                () -> new TaskFactory(Mockito.mock(SkillfulCharacter.class), null));
    }
}
