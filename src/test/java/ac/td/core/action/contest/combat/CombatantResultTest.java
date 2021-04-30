package ac.td.core.action.contest.combat;

import ac.td.core.action.contest.ContestException;
import ac.td.core.action.contest.ContestResultAlreadyApplied;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

class CombatantResultTest {

    private final Combatant combatant = Mockito.mock(Combatant.class);

    @Test
    public void testNew_CombatantNull() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new CombatantResult(null, StatusType.SUCCESS));
    }

    @Test
    public void testNew_StatusNull() {
        Assertions.assertThrows(NullPointerException.class, () -> new CombatantResult(this.combatant, null));
    }

    @Test
    public void testNew_EffectsNull() {
        Assertions.assertDoesNotThrow(() -> new CombatantResult(this.combatant, StatusType.SUCCESS, null));
    }

    @Test
    public void testGetEffect_Numeric() {
        final Map<String, Integer> effects = Map.of("health", -1);
        final CombatantResult result = new CombatantResult(this.combatant, StatusType.SUCCESS, effects);

        Assertions.assertEquals(-1, result.getEffect("health"));
    }

    @Test
    public void testGetEffect_NoEffect() {
        final CombatantResult result = new CombatantResult(this.combatant, StatusType.SUCCESS);

        Assertions.assertEquals(0, result.getEffect("health"));
    }

    @Test
    public void testGetEffect_EffectNull() {
        final Map<String, Integer> effects = new HashMap<>(1);
        effects.put("health", null);
        final CombatantResult result = new CombatantResult(this.combatant, StatusType.SUCCESS, effects);

        Assertions.assertEquals(0, result.getEffect("health"));
    }

    @Test
    public void testApplyResultsToCombatant_WithHealthBeingAffected() throws ContestException {
        final Combatant combatant = Mockito.mock(Combatant.class);
        Mockito.when(combatant.get("health")).thenReturn(2);
        final Map<String, Integer> effects = Map.of("health", -1);
        final CombatantResult result = new CombatantResult(combatant, StatusType.SUCCESS, effects);
        result.applyResultToCombatant();

        Mockito.verify(combatant).get("health");
        Mockito.verify(combatant).set("health", 1);
    }

    @Test
    public void testApplyResultsToCombatant_WithNoEffects() throws ContestException {
        final Combatant combatant = Mockito.mock(Combatant.class);
        final CombatantResult result = new CombatantResult(combatant, StatusType.SUCCESS);
        result.applyResultToCombatant();

        Mockito.verify(combatant, Mockito.never()).get(Mockito.any());
        Mockito.verify(combatant, Mockito.never()).set(Mockito.any(), Mockito.anyInt());
    }

    @Test
    public void testApplyResultsToCombatant_TwiceThrowsAnException() throws ContestException {
        final CombatantResult result = new CombatantResult(combatant, StatusType.SUCCESS);
        result.applyResultToCombatant();
        Assertions.assertThrows(ContestResultAlreadyApplied.class, result::applyResultToCombatant);
    }

}