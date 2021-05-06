package ac.td.core.action.task.skill;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskCharacterException;
import ac.td.core.action.task.TaskDieFactoryException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

import java.util.Set;

public class Translation extends SkillTask {
    public Translation(final SkillfulCharacter character, final DieFactory dieFactory)
            throws TaskCharacterException, TaskDieFactoryException {
        super(character, dieFactory);
    }

    public Translation(
            final SkillfulCharacter character,
            final DieFactory dieFactory,
            final Set<SpecialtyType> nonDefaultApplicableSpecialties)
            throws TaskCharacterException, TaskDieFactoryException, WrongSpecialtySkillException {
        super(character, dieFactory, nonDefaultApplicableSpecialties);
    }

    @Override
    public int calculateBasicPoolSize() {
        final int attribute = this.character.getAttribute(AttributeType.INTELLIGENCE);
        final int skill = this.character.getSkill(this.getDrivingSkill());
        final int modifiers = this.calculateModifiersDicePoolSize();

        return attribute + skill + modifiers;
    }

    @Override
    public ActionType getType() {
        return ActionType.INSTANT;
    }

    @Override
    public Set<CategoryType> getCategories() {
        return Set.of(CategoryType.MENTAL);
    }

    @Override
    public Set<SpecialtyType> getDefaultApplicableSpecialties() {
        return Set.of(SpecialtyType.LINGUISTICS);
    }

    @Override
    public SkillType getDrivingSkill() {
        return SkillType.ACADEMICS;
    }
}
