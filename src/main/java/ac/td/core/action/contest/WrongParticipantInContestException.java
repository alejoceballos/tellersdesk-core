package ac.td.core.action.contest;

public class WrongParticipantInContestException extends ContestException {
    public WrongParticipantInContestException(final String message) {
        super(message);
    }
}
