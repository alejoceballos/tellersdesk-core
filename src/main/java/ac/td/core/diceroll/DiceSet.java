package ac.td.core.diceroll;

import java.util.*;
import java.util.stream.Collectors;

public class DiceSet {
    private final Set<Die> dicePool = new HashSet<>();

    protected DiceSet(final Set<Die> dicePool) throws DiceSetException {
        if (Objects.isNull(dicePool)) {
            throw new DiceSetException("Dice pool cannot be null");
        }

        if (dicePool.isEmpty()) {
            throw new DiceSetException("Dice pool cannot be empty");
        }

        if (dicePool.stream().anyMatch(Objects::isNull)) {
            throw new DiceSetException("Dice pool cannot have a null die");
        }

        this.dicePool.addAll(dicePool);
    }

    public DiceSet roll() {
        dicePool.forEach(Die::roll);
        return this;
    }

    public List<Integer> getLastResult() {
        return dicePool.stream().map(Die::getLastResult).collect(Collectors.toList());
    }
}
