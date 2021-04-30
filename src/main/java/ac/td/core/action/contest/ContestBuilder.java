package ac.td.core.action.contest;

public interface ContestBuilder<C extends Contest<?, P>, P extends Participant> {
    @SuppressWarnings("unchecked")
    ContestBuilder<C, P> addParticipant(final P... participant) throws ContestException;
    C build() throws ContestException;
}