package ac.td.core.diceroll;

import java.util.Random;

public class Die {
    private final Random random;
    private Integer lastResult = null;

    protected Die(final Random random) {
        this.random = random;
    }

    public Integer getLastResult() {
        return lastResult;
    }

    public Die roll() {
        final int faces = 10;
        this.lastResult = random.nextInt(faces - 1) + 1;
        return this;
    }
}
