package ac.td.core.diceroll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

class DieTest {

    @Test
    public void rollTenTimes() {
        final Die die = new Die(new Random());

        for (int i = 0; i < 10; i++) {
            final int actual = die.roll().getLastResult();
            Assertions.assertTrue(actual > 0 && actual < 11, String.format("%d is not between 1 and 10", actual));
        }
    }

}