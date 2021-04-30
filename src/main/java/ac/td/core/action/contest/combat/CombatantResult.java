package ac.td.core.action.contest.combat;

import ac.td.core.action.contest.ContestException;
import ac.td.core.action.contest.ContestResultAlreadyApplied;

import java.util.HashMap;
import java.util.Map;

public class CombatantResult {

    private final Combatant combatant;
    private final StatusType statusType;
    private final Map<String, Integer> effects = new HashMap<>();

    private boolean applied = false;

    private void validateNotNull(final Object... args) {
        for (Object arg: args) {
            if (arg == null) throw new NullPointerException("Argument cannot be null");
        }
    }

    protected CombatantResult(final Combatant combatant, final StatusType statusType) {
        validateNotNull(combatant, statusType);
        this.combatant = combatant;
        this.statusType = statusType;
    }

    protected CombatantResult(final Combatant combatant, final StatusType statusType, final Map<String, Integer> effects) {
        this(combatant, statusType);
        if (effects != null) {
            this.effects.putAll(effects);
        }
    }

    public Combatant getCombatant() {
        return combatant;
    }

    public StatusType getStatus() {
        return this.statusType;
    }

    public int getEffect(final String effect) {
        final Integer effectResult = this.effects.get(effect);
        return effectResult == null ? 0 : effectResult;
    }

    public void applyResultToCombatant() throws ContestException {
        if (applied) throw new ContestResultAlreadyApplied("Combat result already applied to combatant");
        this.effects.forEach((effect, value) -> combatant.set(effect, this.combatant.get(effect) + value));
        applied = true;
    }
}
