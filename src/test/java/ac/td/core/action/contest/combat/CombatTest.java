package ac.td.core.action.contest.combat;

import ac.td.core.action.contest.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CombatTest {

    @Test
    public void testBuilder_New() {
        Assertions.assertNotNull(Combat.builder());
    }

    @Test
    public void testBuilder_build() throws ContestException {
        Assertions.assertNotNull(Combat.builder()
                .addParticipant(Mockito.mock(Defender.class), Mockito.mock(Attacker.class)).build());
    }

    @Test
    public void testBuilder_buildWithoutCombatants() {
        Assertions.assertThrows(NotEnoughParticipantsInContestException.class, () -> Combat.builder().build());
    }

    @Test
    public void testBuilder_buildWithOneCombatant() {
        Assertions.assertThrows(
                NotEnoughParticipantsInContestException.class,
                () -> Combat.builder().addParticipant(Mockito.mock(Defender.class)).build());
    }

    @Test
    public void testBuilder_addNullCombatant() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Combat.builder().addParticipant((Combatant) null));
    }

    @Test
    public void testBuilder_addNullCombatantsList() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Combat.builder().addParticipant((Combatant[]) null));
    }

    @Test
    public void testBuilder_addNullCombatantInList() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Combat.builder().addParticipant(new Combatant[]{null}));
    }

    @Test
    public void testBuilder_addNoCombatantsInList() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Combat.builder().addParticipant(new Combatant[0]));
    }

    @Test
    public void testBuilder_addNoCombatant() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Combat.builder().addParticipant());
    }

    @Test
    public void testBuilder_addBlockedBecauseMaxCombatantsNumberWasAlreadyReached() throws ContestException {
        final Combat.Builder builder = Combat.builder().addParticipant(
                Mockito.mock(Attacker.class), Mockito.mock(Defender.class));

        Assertions.assertThrows(
                TooManyParticipantsInContestException.class,
                () -> builder.addParticipant(Mockito.mock(Combatant.class)));
    }

    @Test
    public void testBuilder_addBlockedAsItWillExceedCombatantsNumber() throws ContestException {
        final Combat.Builder builder = Combat.builder().addParticipant(Mockito.mock(Attacker.class));

        Assertions.assertThrows(
                TooManyParticipantsInContestException.class,
                () -> builder.addParticipant(Mockito.mock(Combatant.class), Mockito.mock(Combatant.class)));
    }

    @Test
    public void testBuilder_addNoAttackerOrDefender() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Combat.builder().addParticipant(Mockito.mock(Combatant.class)));
    }

    @Test
    public void testSetProtagonist_WithNullCombatant() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Combat.builder()
                        .addParticipant(Mockito.mock(Combatant.class), Mockito.mock(Combatant.class))
                        .build()
                        .setProtagonist(null));
    }

    @Test
    public void testSetProtagonist_WhenHeIsNoCombatant() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Combat.builder()
                        .addParticipant(Mockito.mock(Combatant.class), Mockito.mock(Combatant.class))
                        .build()
                        .setProtagonist(Mockito.mock(Combatant.class)));
    }

    @Test
    public void testSetProtagonist_WhenHeIsNotAnAttacker() {
        final Defender defender = Mockito.mock(Defender.class);

        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Combat.builder()
                        .addParticipant(defender, Mockito.mock(Combatant.class))
                        .build()
                        .setProtagonist(defender));
    }

    @Test
    public void testPerform_WithoutCombatants() {
        final Combat combat = new Combat();
        Assertions.assertThrows(NotEnoughParticipantsInContestException.class, combat::perform);
    }

    @Test
    public void testPerform_WithoutProtagonist() {
        Assertions.assertThrows(
                WrongParticipantInContestException.class,
                () -> Combat.builder()
                        .addParticipant(Mockito.mock(Combatant.class), Mockito.mock(Combatant.class))
                        .build()
                        .perform());
    }

    @Test
    public void testPerform_AttackSuccessful() throws ContestException {
        final Attacker attacker = Mockito.mock(Attacker.class);
        Mockito.when(attacker.attack()).thenReturn(2);
        Mockito.when(attacker.potentialDamage()).thenReturn(5);

        final Defender defender = Mockito.mock(Defender.class);
        Mockito.when(defender.defend()).thenReturn(1);
        Mockito.when(defender.resistance()).thenReturn(3);

        final CombatResult result = Combat
                .builder()
                .addParticipant(attacker, defender)
                .build()
                .setProtagonist(attacker)
                .perform()
                .getContestResult();

        final CombatantResult attackerResult = result.getResult(attacker);
        final CombatantResult defenderResult = result.getResult(defender);

        Assertions.assertEquals(StatusType.SUCCESS, attackerResult.getStatus());
        Assertions.assertEquals(0, attackerResult.getEffect("health"));

        Assertions.assertEquals(StatusType.FAIL, defenderResult.getStatus());
        Assertions.assertEquals(-2, defenderResult.getEffect("health"));
    }

    @Test
    public void testPerform_AttackFailure() throws ContestException {
        final Attacker attacker = Mockito.mock(Attacker.class);
        Mockito.when(attacker.attack()).thenReturn(3);
        Mockito.when(attacker.potentialDamage()).thenReturn(4);

        final Defender defender = Mockito.mock(Defender.class);
        Mockito.when(defender.defend()).thenReturn(3);
        Mockito.when(defender.resistance()).thenReturn(2);

        final CombatResult result = Combat
                .builder()
                .addParticipant(attacker, defender)
                .build()
                .setProtagonist(attacker)
                .perform()
                .getContestResult();

        final CombatantResult attackerResult = result.getResult(attacker);
        final CombatantResult defenderResult = result.getResult(defender);

        Assertions.assertEquals(StatusType.FAIL, attackerResult.getStatus());
        Assertions.assertEquals(0, attackerResult.getEffect("health"));

        Assertions.assertEquals(StatusType.SUCCESS, defenderResult.getStatus());
        Assertions.assertEquals(0, defenderResult.getEffect("health"));
    }

    @Test
    public void testGetResult_WithoutPerforming() {
        final Combat combat = new Combat();
        Assertions.assertThrows(ContestNotPerformedException.class, combat::getContestResult);
    }


}
