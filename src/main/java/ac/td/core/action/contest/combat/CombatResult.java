package ac.td.core.action.contest.combat;

import ac.td.core.action.contest.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CombatResult implements ContestResult<CombatantResult, Combatant> {
    private final Map<Combatant, CombatantResult> results = new HashMap<>();

    protected CombatResult(final CombatantResult... results) throws ContestException {
        this.validateCombatantResults(results);
        Arrays.stream(results).forEach(result -> this.results.put(result.getCombatant(), result));
    }

    private void validateCombatantResults(final CombatantResult... results) throws ContestException {
        if (results.length < 2) throw new NotEnoughParticipantsResultException("Not enough participant results provided");
        if (results.length > 2) throw new TooManyParticipantsResultException("Too many participant results provided");
        for (CombatantResult result : results) {
            if (result == null) throw new WrongParticipantResultException("Null participant result not allowed");
        }
    }

    @Override
    public CombatantResult getResult(final Combatant combatant) throws WrongParticipantResultException {
        if (combatant == null || !this.results.containsKey(combatant)) {
            throw new WrongParticipantResultException("Not a racer");
        }

        return this.results.get(combatant);
    }

    @Override
    public Map<Combatant, CombatantResult> getResults() {
        return new HashMap<>(this.results);
    }

    @Override
    public void applyResults() throws ContestException {
        for (CombatantResult combatantResult : this.results.values()) {
            combatantResult.applyResultToCombatant();
        }
    }
}
