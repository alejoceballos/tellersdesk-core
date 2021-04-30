package ac.td.core.action.contest.combat;

import ac.td.core.action.contest.*;

import java.util.*;

public class Combat implements Contest<CombatResult, Combatant> {
    private final Set<Combatant> combatants = new HashSet<>();
    private Combatant protagonist;
    private CombatResult result;

    protected Combat() {}

    @Override
    public Combat perform() throws ContestException {
        if (this.combatants.size() < 2) {
            throw new NotEnoughParticipantsInContestException(
                    String.format("%d participant(s) added", this.combatants.size()));
        }

        if (this.protagonist == null) {
            throw new WrongParticipantInContestException("No protagonist set");
        }

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        final Attacker attacker = (Attacker) combatants.stream()
                .filter(participant -> participant == this.protagonist)
                .findFirst()
                .get();

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        final Defender defender = (Defender) combatants.stream()
                .filter(participant -> participant != protagonist)
                .findFirst()
                .get();

        if (attacker.attack() > defender.defend()) {
            final int damageInflicted = (attacker.potentialDamage() - defender.resistance()) * -1;
            this.result = new CombatResult(
                    new CombatantResult(attacker, StatusType.SUCCESS),
                    new CombatantResult(defender, StatusType.FAIL, Map.of("health", damageInflicted)));
        } else {
            this.result = new CombatResult(
                    new CombatantResult(attacker, StatusType.FAIL),
                    new CombatantResult(defender, StatusType.SUCCESS));
        }

        return this;
    }

    @Override
    public CombatResult getContestResult() throws ContestNotPerformedException {
        if (this.result == null) {
            throw new ContestNotPerformedException("No combat has taken place");
        }

        return this.result;
    }

    @Override
    public Combat setProtagonist(final Combatant protagonist) throws WrongParticipantInContestException {
        if (protagonist == null) {
            throw new WrongParticipantInContestException("No protagonist set");
        }

        if (!combatants.contains(protagonist)) {
            throw new WrongParticipantInContestException("Only participants can be protagonists");
        }

        if (!(protagonist instanceof Attacker)) {
            throw new WrongParticipantInContestException("Only attackers can be protagonists");
        }

        this.protagonist = protagonist;

        return this;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements ContestBuilder<Combat, Combatant> {
        private final Combat subject = new Combat();

        @Override
        public Builder addParticipant(final Combatant... combatants) throws ContestException {
            if (combatants == null || Arrays.stream(combatants).anyMatch(Objects::isNull)) {
                throw new WrongParticipantInContestException("Combatant is null");
            }

            if (combatants.length == 0) {
                throw new WrongParticipantInContestException("No combatants added");
            }

            if (this.subject.combatants.size() == 2) {
                throw new TooManyParticipantsInContestException("No more combatants allowed");
            }

            if (this.subject.combatants.size() + combatants.length > 2) {
                throw new TooManyParticipantsInContestException("Only two combatants allowed");
            }

            for (final Combatant combatant : combatants) {
                if (!(combatant instanceof Attacker) && !(combatant instanceof Defender)) {
                    throw new WrongParticipantInContestException("Combatant must be attacker or defender");
                }
            }

            this.subject.combatants.addAll(Arrays.asList(combatants));

            return this;
        }

        @Override
        public Combat build() throws ContestException {
            if (this.subject.combatants.size() < 2) {
                throw new NotEnoughParticipantsInContestException("Should have 2 combatants");
            }

            return this.subject;
        }
    }
}
