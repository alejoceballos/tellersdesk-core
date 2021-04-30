package ac.td.core.diceroll;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DieFactory {
    private final Random randomizer;

    public DieFactory(final Random randomizer) {
        this.randomizer = randomizer;
    }

    public Die create() {
        return new Die(this.randomizer);
    }

    public Set<Die> create(final int dicePoolSize) {
        return dicePoolSize > 0 ?
                Stream.generate(() -> new Die(this.randomizer)).limit(dicePoolSize).collect(Collectors.toSet())
                : new HashSet<>(0);
    }
}
