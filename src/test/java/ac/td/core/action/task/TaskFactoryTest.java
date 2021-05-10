package ac.td.core.action.task;

import ac.td.core.action.task.skill.*;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SpecialtyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Set;

class TaskFactoryTest {

    private final Map<Class<? extends SkillTask>, SpecialtyType> skillsClasses = Map.of(
            DatabaseSearches.class, SpecialtyType.USER_INTERFACE_DESIGN,
            HackIntoComputerSystem.class, SpecialtyType.USER_INTERFACE_DESIGN,
            InternetSearches.class, SpecialtyType.USER_INTERFACE_DESIGN,
            RecallHistoricalFacts.class, SpecialtyType.ENGLISH_LITERATURE,
            Research.class, SpecialtyType.ENGLISH_LITERATURE,
            Translation.class, SpecialtyType.RESEARCH
    );

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
    public void create_default() {
        this.skillsClasses.keySet().forEach(skillClass -> {
            SkillTask task = null;

            try {
                task = new TaskFactory(
                        Mockito.mock(SkillfulCharacter.class),
                        Mockito.mock(DieFactory.class)
                ).create(skillClass);
            } catch (final TaskException ignored) { }

            Assertions.assertNotNull(task);
        });
    }

    @Test
    public void create_withNonDefaultSpecialty() {
        this.skillsClasses.forEach((key, value) -> {
            SkillTask task = null;

            try {
                task = new TaskFactory(
                        Mockito.mock(SkillfulCharacter.class),
                        Mockito.mock(DieFactory.class)
                ).create(key, Set.of(value));
            } catch (final TaskException ignored) {
            }

            Assertions.assertNotNull(task);
        });
    }

}
