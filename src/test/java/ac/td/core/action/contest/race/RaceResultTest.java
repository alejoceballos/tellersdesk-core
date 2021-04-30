package ac.td.core.action.contest.race;

import ac.td.core.action.contest.ContestException;
import ac.td.core.action.contest.NotEnoughParticipantsResultException;
import ac.td.core.action.contest.WrongParticipantResultException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RaceResultTest {

    private final Racer racer = Mockito.mock(Racer.class);

    @Test
    public void testNew_WithNoRacer() {
        Assertions.assertThrows(NotEnoughParticipantsResultException.class, RaceResult::new);
    }

    @Test
    public void testNew_WithOnlyOneRacer() {
        Assertions.assertThrows(
                NotEnoughParticipantsResultException.class,
                () -> new RaceResult(this.racer));
    }

    @Test
    public void testNew_WithNullRacer() {
        Assertions.assertThrows(
                WrongParticipantResultException.class,
                () -> new RaceResult(this.racer, null));
    }

    @Test
    public void testGetResult_WhenRacerIsNotInContest() {
        Assertions.assertThrows(
                WrongParticipantResultException.class,
                () -> new RaceResult(this.racer, Mockito.mock(Racer.class))
                        .getResult(Mockito.mock(Racer.class)));
    }

    @Test
    public void testApplyResults() {
        Assertions.assertThrows(
                ContestException.class,
                () -> new RaceResult(this.racer, Mockito.mock(Racer.class))
                        .applyResults());
    }

}
