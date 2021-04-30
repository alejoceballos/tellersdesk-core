package ac.td.core.diceroll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Objects;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DieFactoryTest {

    @Test
    void create() {
        Assertions.assertNotNull(new DieFactory(Mockito.mock(Random.class)).create());
    }

    @Test
    void createDicePool() {
        final Set<Die> dice = new DieFactory(Mockito.mock(Random.class)).create(10);
        Assertions.assertEquals(10, dice.size());
        Assertions.assertFalse(dice.stream().anyMatch(Objects::isNull));
    }
}