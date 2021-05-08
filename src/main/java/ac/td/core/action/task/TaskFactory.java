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

    @SuppressWarnings("unchecked")
    public <T extends SkillTask> T create(final Class<? extends SkillTask> taskClass) {
        try {
            return (T) taskClass
                    .getDeclaredConstructor(SkillfulCharacter.class, DieFactory.class)
                    .newInstance(this.character, this.dieFactory);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends SkillTask> T create(
            final Class<? extends SkillTask> taskClass,
            final Set<SpecialtyType> nonDefaultApplicableSpecialties) {
        try {
            return (T) taskClass
                    .getDeclaredConstructor(SkillfulCharacter.class, DieFactory.class, Set.class)
                    .newInstance(this.character, this.dieFactory, nonDefaultApplicableSpecialties);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
