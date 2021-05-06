package ac.td.core.action.task;

import ac.td.core.action.task.skill.*;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SpecialtyType;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;

public class TaskFactory {
    private final SkillfulCharacter character;
    private final DieFactory dieFactory;

    public TaskFactory(final SkillfulCharacter character, final DieFactory dieFactory)
            throws TaskCharacterException, TaskDieFactoryException {

        if (Objects.isNull(character)) {
            throw new TaskCharacterException("Character cannot be null");
        }

        if (dieFactory == null) {
            throw new TaskDieFactoryException("Die factory cannot be null");
        }

        this.character = character;
        this.dieFactory = dieFactory;
    }

    public RecallHistoricalFacts createRecallHistoricalFacts() throws TaskCharacterException, TaskDieFactoryException {
        return new RecallHistoricalFacts(this.character, this.dieFactory);
    }

    public RecallHistoricalFacts createRecallHistoricalFacts(final Set<SpecialtyType> nonDefaultApplicableSpecialties)
            throws TaskCharacterException, TaskDieFactoryException, WrongSpecialtySkillException {
        return new RecallHistoricalFacts(this.character, this.dieFactory, nonDefaultApplicableSpecialties);
    }

    public Research createResearch() throws TaskCharacterException, TaskDieFactoryException {
        return new Research(this.character, this.dieFactory);
    }

    public Research createResearch(final Set<SpecialtyType> nonDefaultApplicableSpecialties)
            throws TaskCharacterException, TaskDieFactoryException, WrongSpecialtySkillException {
        return new Research(this.character, this.dieFactory, nonDefaultApplicableSpecialties);
    }

    public Translation createTranslation() throws TaskCharacterException, TaskDieFactoryException {
        return new Translation(this.character, this.dieFactory);
    }

    public Translation createTranslation(final Set<SpecialtyType> nonDefaultApplicableSpecialties)
            throws TaskCharacterException, TaskDieFactoryException, WrongSpecialtySkillException {
        return new Translation(this.character, this.dieFactory, nonDefaultApplicableSpecialties);
    }

    public HackIntoComputerSystem createHackIntoComputerSystem()
            throws TaskCharacterException, TaskDieFactoryException {
        return new HackIntoComputerSystem(this.character, this.dieFactory);
    }

    public HackIntoComputerSystem createHackIntoComputerSystem(final Set<SpecialtyType> nonDefaultApplicableSpecialties)
            throws TaskCharacterException, TaskDieFactoryException, WrongSpecialtySkillException {
        return new HackIntoComputerSystem(this.character, this.dieFactory, nonDefaultApplicableSpecialties);
    }

}
