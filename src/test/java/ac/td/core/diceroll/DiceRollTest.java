package ac.td.core.diceroll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

class DiceRollTest {

    @Test
    public void newInstance_NullRandomizer() {
        Assertions.assertThrows(DiceRollException.class, () -> new DiceRoll(null));
    }

    @Test
    public void newInstance_DefaultValues() throws DiceRollException {
        final DiceRoll diceRoll = new DiceRoll(Mockito.mock(DieFactory.class));
        Assertions.assertEquals(1, diceRoll.getDicePoolSize());
        Assertions.assertEquals(DiceReRollType.AGAIN_10, diceRoll.getDiceReRollType());
        Assertions.assertTrue(diceRoll.isChanceRoll());
    }

    @Test
    public void setReRollType_Null() throws DiceRollException {
        final DiceRoll diceRoll = new DiceRoll(Mockito.mock(DieFactory.class)).setReRollType(null);
        Assertions.assertEquals(DiceReRollType.AGAIN_10, diceRoll.getDiceReRollType());
    }

    @Test
    public void setReRollType_NotNull() throws DiceRollException {
        final DiceRoll diceRoll = new DiceRoll(Mockito.mock(DieFactory.class));

        for (final DiceReRollType diceReRollType : DiceReRollType.values()) {
            diceRoll.setReRollType(diceReRollType);
            Assertions.assertEquals(diceReRollType, diceRoll.getDiceReRollType());
        }
    }

    @Test
    public void setDiceAmount_Zero() throws DiceRollException {
        final DiceRoll diceRoll = new DiceRoll(Mockito.mock(DieFactory.class)).setDicePoolSize(0);
        Assertions.assertEquals(1, diceRoll.getDicePoolSize());
        Assertions.assertTrue(diceRoll.isChanceRoll());
    }

    @Test
    public void setDiceAmount_Negative() throws DiceRollException {
        final DiceRoll diceRoll = new DiceRoll(Mockito.mock(DieFactory.class)).setDicePoolSize(0);
        Assertions.assertEquals(1, diceRoll.getDicePoolSize());
        Assertions.assertTrue(diceRoll.isChanceRoll());
    }

    @Test
    public void setDiceAmount_Positive() throws DiceRollException {
        final DiceRoll diceRoll = new DiceRoll(Mockito.mock(DieFactory.class)).setDicePoolSize(1);
        Assertions.assertEquals(1, diceRoll.getDicePoolSize());
        Assertions.assertFalse(diceRoll.isChanceRoll());
    }

    @Test
    public void analyze_NoRoll() throws DiceRollException {
        final DieFactory dieFactory = new DieFactory(new Random());
        final DiceRoll diceRoll = new DiceRoll(dieFactory).setDicePoolSize(1).perform();

        try {
            diceRoll.analyze(2);
            throw new DiceRollAnalysisException("Should have thrown NON_EXISTENT_ROLL_SELECTED");
        } catch (final DiceRollAnalysisException e) {
            Assertions.assertEquals(
                    DiceRollAnalysisException.AnalysisExceptionType.NON_EXISTENT_ROLL_SELECTED,
                    e.getExceptionType());
        }
    }

    @Test
    public void analyze_IllegalRoll() throws DiceRollException {
        final DieFactory dieFactory = new DieFactory(new Random());
        final DiceRoll diceRoll = new DiceRoll(dieFactory).perform();

        try {
            diceRoll.analyze(-1);
            throw new DiceRollAnalysisException("Should have thrown INCORRECT_ROLL_NUMBER");
        } catch (final DiceRollAnalysisException e) {
            Assertions.assertEquals(
                    DiceRollAnalysisException.AnalysisExceptionType.INCORRECT_ROLL_NUMBER,
                    e.getExceptionType());
        }
    }

    @Test
    public void analyze_NonexistentRoll() throws DiceRollException {
        final DieFactory dieFactory = new DieFactory(new Random());
        final DiceRoll diceRoll = new DiceRoll(dieFactory).perform();

        Assertions.assertThrows(DiceRollAnalysisException.class, () -> diceRoll.analyze(2));
    }

    @Test
    public void analyze_SuccessReRoll_status_OnGoing() throws DiceRollException {
        final Random randomizer = Mockito.mock(Random.class);
        Mockito.when(randomizer.nextInt(9)).thenReturn(10);
        final DieFactory dieFactory = new DieFactory(randomizer);
        final DiceRoll diceRoll = new DiceRoll(dieFactory).setDicePoolSize(1).perform();

        Assertions.assertEquals(DiceRollAnalysisType.SUCCESS_RE_ROLL_NEEDED, diceRoll.analyze());
        Assertions.assertEquals(DiceRollStatusType.ON_GOING, diceRoll.status());
    }

    @Test
    public void analyze_CompletedRoll_status_Success() throws DiceRollException {
        final Random randomizer = Mockito.mock(Random.class);
        Mockito.when(randomizer.nextInt(9)).thenReturn(7);
        final DieFactory dieFactory = new DieFactory(randomizer);
        final DiceRoll diceRoll = new DiceRoll(dieFactory).setDicePoolSize(1).perform();

        Assertions.assertEquals(DiceRollAnalysisType.COMPLETED_ROLL, diceRoll.analyze());
        Assertions.assertEquals(DiceRollStatusType.SUCCESS, diceRoll.status());
        Assertions.assertEquals(1, diceRoll.result().size());
        Assertions.assertEquals(8, diceRoll.result().get(0));
    }

    @Test
    public void analyze_CompletedRoll_status_ExceptionalSuccess() throws DiceRollException {
        final Random randomizer = Mockito.mock(Random.class);
        final List<Integer> results = Arrays.asList(10, 10, 10, 10, 10, 8);

        OngoingStubbing<Integer> stub = Mockito.when(randomizer.nextInt(9));
        for (final int result : results) {
            stub = stub.thenReturn(result - 1);
        }
        final DieFactory dieFactory = new DieFactory(randomizer);
        final DiceRoll diceRoll = new DiceRoll(dieFactory).setDicePoolSize(1);

        for (int idx = 0; idx < 5; idx++) {
            diceRoll.perform();
            Assertions.assertEquals(DiceRollAnalysisType.SUCCESS_RE_ROLL_NEEDED, diceRoll.analyze());
            Assertions.assertEquals(DiceRollStatusType.ON_GOING, diceRoll.status());
        }

        diceRoll.perform();
        Assertions.assertEquals(DiceRollAnalysisType.COMPLETED_ROLL, diceRoll.analyze());
        Assertions.assertEquals(DiceRollStatusType.EXCEPTIONAL_SUCCESS, diceRoll.status());
        Assertions.assertEquals(6, diceRoll.result().size());

        Assertions.assertEquals(results.size(), diceRoll.result().size());
        Assertions.assertEquals(results.size(), diceRoll.result().size());
    }

    @Test
    public void analyze_CompletedRoll_ChanceRoll_status_Success() throws DiceRollException {
        final Random randomizer = Mockito.mock(Random.class);
        Mockito.when(randomizer.nextInt(9)).thenReturn(9);
        final DieFactory dieFactory = new DieFactory(randomizer);
        final DiceRoll diceRoll = new DiceRoll(dieFactory).perform();

        Assertions.assertEquals(DiceRollAnalysisType.COMPLETED_ROLL, diceRoll.analyze());
        Assertions.assertEquals(DiceRollStatusType.SUCCESS, diceRoll.status());
        Assertions.assertEquals(1, diceRoll.result().size());
        Assertions.assertEquals(10, diceRoll.result().get(0));
    }

    @Test
    public void analyze_CompletedRoll_status_Failure() throws DiceRollException {
        final Random randomizer = Mockito.mock(Random.class);
        Mockito.when(randomizer.nextInt(9)).thenReturn(6);
        final DieFactory dieFactory = new DieFactory(randomizer);
        final DiceRoll diceRoll = new DiceRoll(dieFactory).setDicePoolSize(1).perform();

        Assertions.assertEquals(DiceRollAnalysisType.COMPLETED_ROLL, diceRoll.analyze());
        Assertions.assertEquals(DiceRollStatusType.FAILURE, diceRoll.status());
        Assertions.assertEquals(1, diceRoll.result().size());
        Assertions.assertEquals(7, diceRoll.result().get(0));
    }

    @Test
    public void analyze_CompletedRoll_status_DramaticFailure() throws DiceRollException {
        final Random randomizer = Mockito.mock(Random.class);
        Mockito.when(randomizer.nextInt(9)).thenReturn(0);
        final DieFactory dieFactory = new DieFactory(randomizer);
        final DiceRoll diceRoll = new DiceRoll(dieFactory).perform();

        Assertions.assertEquals(DiceRollAnalysisType.COMPLETED_ROLL, diceRoll.analyze());
        Assertions.assertEquals(DiceRollStatusType.DRAMATIC_FAILURE, diceRoll.status());
        Assertions.assertEquals(1, diceRoll.result().size());
        Assertions.assertEquals(1, diceRoll.result().get(0));
    }

    @Test
    public void analyze_CompletedRoll_ChanceRoll_status_Failure() throws DiceRollException {
        final Random randomizer = Mockito.mock(Random.class);
        Mockito.when(randomizer.nextInt(9)).thenReturn(6);
        final DieFactory dieFactory = new DieFactory(randomizer);
        final DiceRoll diceRoll = new DiceRoll(dieFactory).perform();

        Assertions.assertEquals(DiceRollAnalysisType.COMPLETED_ROLL, diceRoll.analyze());
        Assertions.assertEquals(DiceRollStatusType.FAILURE, diceRoll.status());
        Assertions.assertEquals(1, diceRoll.result().size());
        Assertions.assertEquals(7, diceRoll.result().get(0));
    }
}