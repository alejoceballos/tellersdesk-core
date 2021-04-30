package ac.td.core.action.contest.race;

import ac.td.core.action.contest.*;

import java.util.*;

public class Race implements Contest<RaceResult, Racer> {
    private final Set<Racer> racers = new HashSet<>();
    private RaceResult result;

    protected Race() {}

    @Override
    public Race perform() throws ContestException {
        if (racers.size() < 2) {
            throw new NotEnoughParticipantsInContestException("Should have at least 2 participants");
        }

        this.result = new RaceResult(
                racers.stream()
                        .sorted(Comparator.comparing(racer -> racer.race() * -1))
                        .toArray(Racer[]::new));

        return this;
    }

    @Override
    public RaceResult getContestResult() throws ContestNotPerformedException {
        if (this.result == null) {
            throw new ContestNotPerformedException("No race has taken place yet");
        }

        return this.result;
    }

    @Override
    public Contest<RaceResult, Racer> setProtagonist(final Racer protagonist) throws WrongParticipantInContestException {
        if (protagonist == null) {
            throw new WrongParticipantInContestException("No protagonist set");
        }

        if (!racers.contains(protagonist)) {
            throw new WrongParticipantInContestException("Only participants can be protagonists");
        }

        return this;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements ContestBuilder<Race, Racer> {
        private final Race subject = new Race();

        @Override
        public Builder addParticipant(final Racer... racers) throws ContestException {
            if (racers == null || Arrays.stream(racers).anyMatch(Objects::isNull)) {
                throw new WrongParticipantInContestException("Racer is null");
            }

            if (racers.length == 0) {
                throw new WrongParticipantInContestException("No racers added");
            }

            this.subject.racers.addAll(Arrays.asList(racers));

            return this;
        }

        @Override
        public Race build() throws ContestException {
            if (this.subject.racers.size() < 2) {
                throw new NotEnoughParticipantsInContestException("Should have at least 2 racers");
            }

            return this.subject;
        }
    }
}
