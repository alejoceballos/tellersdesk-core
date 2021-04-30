package ac.td.core.session;

public class SessionAlreadyBuiltException extends SessionBuilderException {
    public SessionAlreadyBuiltException(final String message) {
        super(message);
    }
}
