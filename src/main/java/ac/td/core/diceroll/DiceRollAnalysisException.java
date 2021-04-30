package ac.td.core.diceroll;

public class DiceRollAnalysisException extends DiceRollException {
    public enum AnalysisExceptionType {
        CUSTOM_ANALYSIS_ERROR,
        INCORRECT_ROLL_NUMBER,
        NON_EXISTENT_ROLL_SELECTED
    }

    private AnalysisExceptionType exceptionType = AnalysisExceptionType.CUSTOM_ANALYSIS_ERROR;

    public DiceRollAnalysisException(final String message) {
        super(message);
    }

    public DiceRollAnalysisException(final AnalysisExceptionType exceptionType) {
        super(exceptionType.name());
        this.exceptionType = exceptionType;
    }

    public AnalysisExceptionType getExceptionType() {
        return exceptionType;
    }
}
