package ac.td.core;

import ac.td.core.action.task.TaskException;
import ac.td.core.character.*;
import ac.td.core.diceroll.DiceRollException;
import ac.td.core.session.Session;
import ac.td.core.session.SessionBuilderException;
import ac.td.core.skill.SpecialtyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

public class RolePlayingTest {

    private enum PROFILE {
        COP, PARAMEDIC, SCHOLAR
    }

    @Test
    public void testPlay() throws TellersDeskCoreException {
        final Map<PROFILE, BaseCharacter> characters = Map.of(
                PROFILE.COP, this.createTheCop(),
                PROFILE.PARAMEDIC, this.createTheParamedic(),
                PROFILE.SCHOLAR, this.createTheScholar());

        final Session gameSession = this.createGameSession(characters.values());

        this.playScene_001_TheCop(gameSession, characters.get(PROFILE.COP));
        this.playScene_002_TheParamedic(gameSession, characters.get(PROFILE.PARAMEDIC));
    }

    private BaseCharacter createTheCop() throws CharacterException {
        final String concept = "I present you a cop. A rookie one. Full of pride and sense of justice. His father " +
                "was a cop, his grandfather was a cop and he always felt that his destiny was naturally, become a " +
                "cop, and a good one! Regarding his mother though, she left when he was recently born and never had " +
                "news about her. His father died during a round when he was young and he was raised by his grandfather";
        final BaseCharacter cop = CharacterAssembler.createBeginnerAssembler()
                // Step One: Character Concept
                .setConcept(concept)
                // Step Two: Anchors
                .setVirtue(VirtueType.JUSTICE)
                .setVice(ViceType.PRIDE)
                // Step Three: Select Attributes
                .setAttributesPriorities(
                        CategoryType.PHYSICAL,
                        CategoryType.SOCIAL,
                        CategoryType.MENTAL)
                .setPhysicalAttributes(1, 2, 2)
                .setSocialAttributes(2, 1, 1)
                .setMentalAttributes(1, 1, 1)
                // Step Four: Select Skills
                .setSkillsPriorities(
                        CategoryType.PHYSICAL,
                        CategoryType.SOCIAL,
                        CategoryType.MENTAL)
                .setPhysicalSkills(2, 2, 1, 3, 0, 0, 1, 2)
                .setSocialSkills(0, 1, 0, 2, 1, 0, 1, 2)
                .setMentalSkills(0, 0, 0, 3, 1, 0, 0, 0)
                // Step Five: Pick Skill Specialties
                .setSpecialties(SpecialtyType.DUMMY_1, SpecialtyType.DUMMY_2, SpecialtyType.DUMMY_3)
                // Step Six: Merits
                // Step Seven: Determine Advantages
                // Finally assemble the character
                .assemble();

        Assertions.assertEquals(0, cop.getExperiencePoints());

        return cop;
    }

    private BaseCharacter createTheParamedic() throws CharacterException {
        final String concept = "So, here is the medical aid that recently finished your corresponding EMT-P course. " +
                "Coming from a low-income family, considers this job the first step until enough money is saved to " +
                "pay for a good Medical School. When this goal is achieved, the paramedic will give his sick mother " +
                "and two siblings a nice home to live in a nice neighborhood, making things right for his family for " +
                "the first time. Until then, there are a lot of non sleeping nights to come";
        final BaseCharacter paramedic = CharacterAssembler.createBeginnerAssembler()
                // Step One: Character Concept
                .setConcept(concept)
                // Step Two: Anchors
                .setVirtue(VirtueType.HOPE)
                .setVice(ViceType.WRATH)
                // Step Three: Select Attributes
                .setAttributesPriorities(
                        CategoryType.MENTAL,
                        CategoryType.PHYSICAL,
                        CategoryType.SOCIAL)
                .setMentalAttributes(1, 2, 2)
                .setPhysicalAttributes(1, 1, 2)
                .setSocialAttributes(2, 0, 1)
                // Step Four: Select Skills
                .setSkillsPriorities(
                        CategoryType.PHYSICAL,
                        CategoryType.MENTAL,
                        CategoryType.SOCIAL)
                .setPhysicalSkills(3, 1, 3, 0, 0, 0, 2, 2)
                .setMentalSkills(1, 1, 0, 0, 4, 0, 0, 1)
                .setSocialSkills(0, 2, 0, 0, 1, 1, 0, 0)
                // Step Five: Pick Skill Specialties
                .setSpecialties(SpecialtyType.DUMMY_1, SpecialtyType.DUMMY_2, SpecialtyType.DUMMY_3)
                // Step Six: Merits
                // Step Seven: Determine Advantages
                // Finally assemble the character
                .assemble();

        Assertions.assertEquals(0, paramedic.getExperiencePoints());

        return paramedic;
    }

    private BaseCharacter createTheScholar() throws CharacterException {
        final String concept = "The nerd, the geek, the moth of the book! Born with wealth, his whole life revolved " +
                "around reading ... And he read so much that he began to detect some idiosyncrasies in our society " +
                "that made him interested in the realm of pseudoscience. Of course, it made him a joke in the " +
                "scientific world and culminated in his disinheritance. Now he tries to prove his theories about all " +
                "kinds of mystical or paranormal occurrences to allow the occult to enter the respected scientific " +
                "environment.";
        final BaseCharacter scholar = CharacterAssembler.createBeginnerAssembler()
                // Step One: Character Concept
                .setConcept(concept)
                // Step Two: Anchors
                .setVirtue(VirtueType.PRUDENCE)
                .setVice(ViceType.ENVY)
                // Step Three: Select Attributes
                .setAttributesPriorities(
                        CategoryType.MENTAL,
                        CategoryType.PHYSICAL,
                        CategoryType.SOCIAL)
                .setMentalAttributes(3, 1, 1)
                .setPhysicalAttributes(1, 2, 1)
                .setSocialAttributes(0, 1, 2)
                // Step Four: Select Skills
                .setSkillsPriorities(
                        CategoryType.MENTAL,
                        CategoryType.SOCIAL,
                        CategoryType.PHYSICAL)
                .setMentalSkills(4, 2, 0, 0, 0, 3, 0, 2)
                .setSocialSkills(0, 0, 2, 0, 3, 2, 0, 0)
                .setPhysicalSkills(1, 0, 1, 2, 0, 0, 0, 0)
                // Step Five: Pick Skill Specialties
                .setSpecialties(SpecialtyType.DUMMY_1, SpecialtyType.DUMMY_2, SpecialtyType.DUMMY_3)
                // Step Six: Merits
                // Step Seven: Determine Advantages
                // Finally assemble the character
                .assemble();

        Assertions.assertEquals(0, scholar.getExperiencePoints());

        return scholar;
    }

    private Session createGameSession(final Collection<BaseCharacter> characters) throws SessionBuilderException {
        final Session.Builder builder = Session.builder();

        for (final BaseCharacter character : characters) {
            builder.add(character);
        }

        return builder.add(new Random()).build();
    }

    private void playScene_001_TheCop(final Session gameSession, final BaseCharacter cop) {
        // Currently:
        // During the end of a dull police round, the cop gets called on the radio regarding a fight that ended with
        // one of the parts being stabbed. Your partner is an old, beard, and overweight experienced cop that would
        // rather go home instead of attending a last call of the night.

        // Even though the cop could call it the night and his partner is rumbling around, he turns the police car and
        // runs towards the occurrence. His sense of justice wouldn't let the night end without the chance of making
        // this city a better place to live.

        // NOTE: Store one virtue usage for the session in case the cop has JUSTICE as a virtue.
        gameSession.getCharacterSession(cop).incrementAppliedVirtues(VirtueType.JUSTICE);
    }

    private void playScene_002_TheParamedic(final Session gameSession, final BaseCharacter paramedic) /* throws TaskException, DiceRollException */ {
        // Currently:
        // It has been an uncommon calm night without severe incidents. It has been so quiet that the paramedic got the
        // wheels to drive the ambulance back to the hospital after taking a call that was nothing but a prank while the
        // driver takes a nap. Suddenly the radio warns about a stabbing and calls for nearby ambulances.

        // The paramedic doesn't even bother to wake the driver up. He makes a U-turn so fast that the poor sleeping
        // beauty loses his balance driving his forehead towards the side door window.

        // NOTE: Test for the paramedic's driver skill to know if the U-turn wasn't reckless enough to hurt his
        // colleague.
    }
}
