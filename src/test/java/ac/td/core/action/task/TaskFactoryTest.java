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

    private final Map<Class<? extends SkillTask>, SpecialtyType> skillsClasses = Map.ofEntries(
            Map.entry(DatabaseSearches.class, SpecialtyType.USER_INTERFACE_DESIGN),
            Map.entry(DesignObject.class, SpecialtyType.JURY_RIGGING),
            Map.entry(ExamineCrimeScene.class, SpecialtyType.CRYPTOGRAPHY),
            Map.entry(HackIntoComputerSystem.class, SpecialtyType.USER_INTERFACE_DESIGN),
            Map.entry(IdentifyPattern.class, SpecialtyType.CRYPTOGRAPHY),
            Map.entry(InternetSearches.class, SpecialtyType.USER_INTERFACE_DESIGN),
            Map.entry(LookForFlaws.class, SpecialtyType.JURY_RIGGING),
            Map.entry(RecallHistoricalFacts.class, SpecialtyType.ENGLISH_LITERATURE),
            Map.entry(RepairItem.class, SpecialtyType.NOT_APPLICABLE),
            Map.entry(Research.class, SpecialtyType.ENGLISH_LITERATURE),
            Map.entry(SolveRiddle.class, SpecialtyType.CRIME_SCENES),
            Map.entry(Translation.class, SpecialtyType.RESEARCH)
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
            if (value.equals(SpecialtyType.NOT_APPLICABLE)) {
                return;
            }

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
