package ac.td.core.action.contest;

import java.util.Map;

public interface ContestResult<R, P extends Participant> {
    R getResult(final P participant) throws WrongParticipantResultException;
    Map<P, R> getResults();
    void applyResults() throws ContestException;
}
