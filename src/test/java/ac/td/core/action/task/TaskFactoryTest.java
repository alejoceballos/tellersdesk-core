package ac.td.core.action.task;

import ac.td.core.action.task.skill.HackIntoComputerSystem;
import ac.td.core.action.task.skill.RecallHistoricalFacts;
import ac.td.core.action.task.skill.Research;
import ac.td.core.action.task.skill.Translation;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SpecialtyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

class TaskFactoryTest {

    @Test
    public void new_CharacterNull() {
        Assertions.assertThrows(
                TaskCharacterException.class,
                () -> new TaskFactory(null, Mockito.mock(DieFactory.class)));
    }

    @Test
    public void new_DieFactoryNull() {
        Assertions.assertThrows(
                TaskDieFactoryException.class,
                () -> new TaskFactory(Mockito.mock(SkillfulCharacter.class), null));
    }

    @Test
    public void createRecallHistoricalFacts_default() throws TaskException {
        final RecallHistoricalFacts task = new TaskFactory(
                Mockito.mock(SkillfulCharacter.class),
                Mockito.mock(DieFactory.class)
        ).createRecallHistoricalFacts();

        Assertions.assertNotNull(task);
    }

    @Test
    public void createRecallHistoricalFacts_nonDefault() throws TaskException {
        final RecallHistoricalFacts task = new TaskFactory(
                Mockito.mock(SkillfulCharacter.class),
                Mockito.mock(DieFactory.class)
        ).createRecallHistoricalFacts(Set.of(SpecialtyType.ENGLISH_LITERATURE));

        Assertions.assertNotNull(task);
    }

    @Test
    public void createResearch_default() throws TaskException {
        final Research task = new TaskFactory(
                Mockito.mock(SkillfulCharacter.class),
                Mockito.mock(DieFactory.class)
        ).createResearch();

        Assertions.assertNotNull(task);
    }

    @Test
    public void createResearch_nonDefault() throws TaskException {
        final Research task = new TaskFactory(
                Mockito.mock(SkillfulCharacter.class),
                Mockito.mock(DieFactory.class)
        ).createResearch(Set.of(SpecialtyType.ENGLISH_LITERATURE));

        Assertions.assertNotNull(task);
    }

    @Test
    public void createTranslation_default() throws TaskException {
        final Translation task = new TaskFactory(
                Mockito.mock(SkillfulCharacter.class),
                Mockito.mock(DieFactory.class)
        ).createTranslation();

        Assertions.assertNotNull(task);
    }

    @Test
    public void createTranslation_nonDefault() throws TaskException {
        final Translation task = new TaskFactory(
                Mockito.mock(SkillfulCharacter.class),
                Mockito.mock(DieFactory.class)
        ).createTranslation(Set.of(SpecialtyType.ENGLISH_LITERATURE));

        Assertions.assertNotNull(task);
    }


    @Test
    public void createHackIntoComputerSystem_default() throws TaskException {
        final HackIntoComputerSystem task = new TaskFactory(
                Mockito.mock(SkillfulCharacter.class),
                Mockito.mock(DieFactory.class)
        ).createHackIntoComputerSystem();

        Assertions.assertNotNull(task);
    }

    @Test
    public void createHackIntoComputerSystem_nonDefault() throws TaskException {
        final HackIntoComputerSystem task = new TaskFactory(
                Mockito.mock(SkillfulCharacter.class),
                Mockito.mock(DieFactory.class)
        ).createHackIntoComputerSystem(Set.of(SpecialtyType.HACKING));

        Assertions.assertNotNull(task);
    }

}
