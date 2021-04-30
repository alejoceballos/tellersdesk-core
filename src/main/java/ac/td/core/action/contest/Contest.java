package ac.td.core.action.contest;

public interface Contest<CR extends ContestResult<?, P>, P extends Participant> {
    Contest<CR, P> perform() throws ContestException;
    CR getContestResult() throws ContestNotPerformedException;
    Contest<CR, P> setProtagonist(final P protagonist) throws WrongParticipantInContestException;
}
