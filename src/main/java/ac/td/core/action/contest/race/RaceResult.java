package ac.td.core.action.contest.race;

import ac.td.core.action.contest.*;

import java.util.*;

public class RaceResult implements ContestResult<Integer, Racer> {
    private final List<Racer> results = new ArrayList<>();

    protected RaceResult(final Racer... racers) throws ContestException {
        this.validateRacers(racers);
        results.addAll(Arrays.asList(racers));
    }

    private void validateRacers(final Racer... racers) throws ContestException {
        if (racers.length < 2) throw new NotEnoughParticipantsResultException("Not enough racer results provided");

        for (final Racer racer : racers) {
            if (racer == null) throw new WrongParticipantResultException("Null participant result not allowed");
        }
    }

    @Override
    public Integer getResult(final Racer participant) throws WrongParticipantResultException {
        if (!this.results.contains(participant)) {
            throw new WrongParticipantResultException("Not a racer");
        }

        return this.results.indexOf(participant) + 1;
    }

    @Override
    public Map<Racer, Integer> getResults() {
        final Map<Racer, Integer> results = new HashMap<>(this.results.size());

        for (final Racer racer : this.results) {
            results.put(racer, results.size() + 1);
        }

        return results;
    }

    @Override
    public void applyResults() throws ContestException {
        throw new ContestException("No result to be applied in a race");
    }
}
