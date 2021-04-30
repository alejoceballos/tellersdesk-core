package ac.td.core.action.contest;

public class NotEnoughParticipantsResultException extends ContestException {
    public NotEnoughParticipantsResultException(final String message) {
        super(message);
    }
}
