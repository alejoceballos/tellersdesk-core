package ac.td.core.diceroll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;

import java.util.*;
import java.util.stream.Stream;

class DiceSetTest {

    @Test
    public void new_NullDicePool() {
        Assertions.assertThrows(DiceSetException.class, () -> new DiceSet(null));
    }

    @Test
    public void new_EmptyDicePool() {
        Assertions.assertThrows(DiceSetException.class, () -> new DiceSet(new HashSet<>()));
    }

    @Test
    public void new_NullDieInPool() {
        Assertions.assertThrows(
                DiceSetException.class,
                () -> new DiceSet(Sets.newSet(Mockito.mock(Die.class), null)));
    }

    @Test
    public void testRoll_RealRandomResults() throws DiceRollException {
        final int dicePoolSize = 5;
        final Set<Die> dicePool = new DieFactory(new Random()).create(dicePoolSize);
        final DiceSet diceSet = new DiceSet(dicePool).roll();

        Assertions.assertTrue(diceSet.getLastResult().size() >= dicePoolSize);
        diceSet.getLastResult().forEach(
                actual -> {
                    Assertions.assertTrue(actual > 0, String.format("%d is not 1 or greater", actual));
                    Assertions.assertTrue(actual < 11, String.format("%d is not 10 or lesser", actual));
                });
    }

}