package ac.td.core.action.task.skill;

import ac.td.core.action.task.Task;
import ac.td.core.action.task.TaskCharacterException;
import ac.td.core.action.task.TaskDieFactoryException;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SpecialtyType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class SkillTask extends Task {

    private final Set<SpecialtyType> nonDefaultApplicableSpecialties = new HashSet<>();
    private final SkillMetadata skillMetadata;

    public SkillTask(final SkillfulCharacter character, final DieFactory dieFactory)
            throws TaskCharacterException, TaskDieFactoryException {
        super(character, dieFactory);
        this.skillMetadata = this.getClass().getAnnotation(SkillMetadata.class);
    }

    public SkillTask(
            final SkillfulCharacter character,
            final DieFactory dieFactory,
            final Set<SpecialtyType> nonDefaultApplicableSpecialties)
            throws TaskCharacterException, TaskDieFactoryException, WrongSpecialtySkillException {
        super(character, dieFactory);
        this.skillMetadata = this.getClass().getAnnotation(SkillMetadata.class);

        if (!Objects.isNull(nonDefaultApplicableSpecialties) && !nonDefaultApplicableSpecialties.isEmpty()) {
            final boolean allSpecialtiesFromDrivingSkill = nonDefaultApplicableSpecialties.stream().allMatch(
                    specialty -> specialty.getSkill().equals(this.skillMetadata.drivingSkill())
            );

            if (!allSpecialtiesFromDrivingSkill) {
                throw new WrongSpecialtySkillException("Specialty does not relate to the driving skill");
            }

            this.nonDefaultApplicableSpecialties.addAll(nonDefaultApplicableSpecialties);
        }
    }

    @Override
    public int calculateDicePoolSize() {
        final int attribute = this.character.getAttribute(this.skillMetadata.drivingAttribute());
        final int skill = this.character.getSkill(this.skillMetadata.drivingSkill());
        final int modifiers = this.calculateModifiersDicePoolSize();
        final int specialtyBonus = this.hasApplicableSpecialty() ? 1 : 0;
        final int untrainedSkillPenalty = this.calculateUntrainedSkillPenalty();

        return attribute + skill + modifiers + specialtyBonus + untrainedSkillPenalty;
    }

    protected boolean hasApplicableSpecialty() {
        final Set<SpecialtyType> applicableSpecialties = new HashSet<>(this.nonDefaultApplicableSpecialties);
        applicableSpecialties.addAll(Set.of(this.skillMetadata.defaultApplicableSpecialties()));

        return applicableSpecialties.stream().anyMatch(
                specialty -> this.character.getSpecialties().contains(specialty)
        );
    }

    private int calculateUntrainedSkillPenalty() {
        if (this.hasSkillFamiliarity()) return 0;
        if (Set.of(this.skillMetadata.categories()).contains(CategoryType.MENTAL)) return -3;
        return -1;
    }

    private boolean hasSkillFamiliarity() {
        return character.getSkill(this.skillMetadata.drivingSkill()) > 0;
    }

}
