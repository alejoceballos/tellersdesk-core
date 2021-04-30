package ac.td.core.action.contest.combat;

import ac.td.core.action.contest.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CombatResultTest {

    private final CombatantResult combatantResult = Mockito.mock(CombatantResult.class);

    @Test
    public void testNew_WithNoCombatantResults() {
        Assertions.assertThrows(NotEnoughParticipantsResultException.class, CombatResult::new);
    }

    @Test
    public void testNew_WithOnlyOneCombatantResult() {
        Assertions.assertThrows(
                NotEnoughParticipantsResultException.class,
                () -> new CombatResult(this.combatantResult));
    }

    @Test
    public void testNew_WithMoreThanTwoCombatantResult() {
        final CombatantResult[] results = new CombatantResult[] {
                this.combatantResult, Mockito.mock(CombatantResult.class), Mockito.mock(CombatantResult.class)
        };
        Assertions.assertThrows(TooManyParticipantsResultException.class, () -> new CombatResult(results));
    }

    @Test
    public void testNew_WithNullCombatantResult() {
        Assertions.assertThrows(
                WrongParticipantResultException.class,
                () -> new CombatResult(this.combatantResult, null));
    }

    @Test
    public void testGetResult_WithNullCombatant() {
        Assertions.assertThrows(
                WrongParticipantResultException.class,
                () -> new CombatResult(this.combatantResult, Mockito.mock(CombatantResult.class))
                        .getResult(null));
    }

    @Test
    public void testGetResult_WhenCombatantIsNotInContest() {
        Assertions.assertThrows(
                WrongParticipantResultException.class,
                () -> new CombatResult(this.combatantResult, Mockito.mock(CombatantResult.class))
                        .getResult(Mockito.mock(Combatant.class)));
    }

    @Test
    public void testGetResults() throws ContestException {
        final CombatantResult[] results = new CombatantResult[] {
                Mockito.mock(CombatantResult.class), Mockito.mock(CombatantResult.class)
        };
        for (CombatantResult result : results) {
            Mockito.when(result.getCombatant()).thenReturn(Mockito.mock(Combatant.class));
        }
        final CombatResult subject = new CombatResult(results);

        Assertions.assertEquals(2, subject.getResults().size());
    }

    @Test
    public void testApplyResults() throws ContestException {
        final CombatantResult[] results = new CombatantResult[] {
                Mockito.mock(CombatantResult.class), Mockito.mock(CombatantResult.class)
        };
        for (CombatantResult result : results) {
            Mockito.when(result.getCombatant()).thenReturn(Mockito.mock(Combatant.class));
        }
        final CombatResult subject = new CombatResult(results);
        subject.applyResults();

        for (CombatantResult result : results) {
            Mockito.verify(result).applyResultToCombatant();
        }
    }

}