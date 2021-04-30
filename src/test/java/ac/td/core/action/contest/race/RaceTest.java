package ac.td.core.action.contest.race;

import ac.td.core.action.contest.ContestException;
import ac.td.core.action.contest.NotEnoughParticipantsInContestException;
import ac.td.core.action.contest.WrongParticipantInContestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RaceTest {

    @Test
    public void testBuilder_New() {
        Assertions.assertNotNull(Race.builder());
    }

    @Test
    public void testBuilder_buildWithoutRacers() {
        Assertions.assertThrows(NotEnoughParticipantsInContestException.class, () -> Race.builder().build());
    }

    @Test
    public void testBuilder_buildWithOneRacer() {
        Assertions.assertThrows(
                NotEnoughParticipantsInContestException.class,
                () -> Race.builder().addParticipant(Mockito.mock(Racer.class)).build());
    }

    @Test
    public void testBuilder_addNullRacer() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Race.builder().addParticipant((Racer) null));
    }

    @Test
    public void testBuilder_addNullRacersList() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Race.builder().addParticipant((Racer[]) null));
    }

    @Test
    public void testBuilder_addNullRacerInList() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Race.builder().addParticipant(new Racer[]{null}));
    }

    @Test
    public void testBuilder_addNoRacersInList() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Race.builder().addParticipant(new Racer[0]));
    }

    @Test
    public void testBuilder_addNoRacer() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Race.builder().addParticipant());
    }

    @Test
    public void testSetProtagonist_WithNullRacer() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Race.builder()
                        .addParticipant(Mockito.mock(Racer.class), Mockito.mock(Racer.class))
                        .build()
                        .setProtagonist(null));
    }

    @Test
    public void testSetProtagonist_WhenHeIsNoInRacer() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Race.builder()
                        .addParticipant(Mockito.mock(Racer.class), Mockito.mock(Racer.class))
                        .build()
                        .setProtagonist(Mockito.mock(Racer.class)));
    }

    @Test
    public void testPerform_WithoutRacers() {
        final Race race = new Race();
        Assertions.assertThrows(NotEnoughParticipantsInContestException.class, race::perform);
    }

    @Test
    public void testPerform_Successful() throws ContestException {
        final Racer winner = Mockito.mock(Racer.class);
        Mockito.when(winner.race()).thenReturn(2);

        final Racer looser = Mockito.mock(Racer.class);
        Mockito.when(looser.race()).thenReturn(1);

        final RaceResult result = Race
                .builder()
                .addParticipant(winner, looser)
                .build()
                .perform()
                .getContestResult();

        Assertions.assertEquals(result.getResult(winner), 1);
        Assertions.assertEquals(result.getResults().get(winner), 1);

        Assertions.assertEquals(result.getResult(looser), 2);
        Assertions.assertEquals(result.getResults().get(looser), 2);

        Assertions.assertEquals(result.getResults().size(), 2);
    }

}
