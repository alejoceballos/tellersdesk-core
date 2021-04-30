package ac.td.core.action.contest;

public class NotEnoughParticipantsInContestException extends ContestException {
    public NotEnoughParticipantsInContestException(final String message) {
        super(message);
    }
}
