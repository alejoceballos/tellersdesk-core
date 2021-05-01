package ac.td.core.action.task.skill;

import ac.td.core.action.task.Task;
import ac.td.core.action.task.TaskCharacterException;
import ac.td.core.action.task.TaskDieFactoryException;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SkillType;
import ac.td.core.skill.SpecialtyType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class SkillTask extends Task {

    private final Set<SpecialtyType> nonDefaultApplicableSpecialties = new HashSet<>();

    public SkillTask(final SkillfulCharacter character, final DieFactory dieFactory)
            throws TaskCharacterException, TaskDieFactoryException {
        super(character, dieFactory);
    }

    public SkillTask(
            final SkillfulCharacter character,
            final DieFactory dieFactory,
            final Set<SpecialtyType> nonDefaultApplicableSpecialties)
            throws TaskCharacterException, TaskDieFactoryException, WrongSpecialtySkillException {

        super(character, dieFactory);

        if (!Objects.isNull(nonDefaultApplicableSpecialties) && !nonDefaultApplicableSpecialties.isEmpty()) {
            final boolean allSpecialtiesFromDrivingSkill = nonDefaultApplicableSpecialties.stream().allMatch(
                    specialty -> specialty.getSkill().equals(this.getDrivingSkill())
            );

            if (!allSpecialtiesFromDrivingSkill) {
                throw new WrongSpecialtySkillException("Specialty does not relate to the driving skill");
            }

            this.nonDefaultApplicableSpecialties.addAll(nonDefaultApplicableSpecialties);
        }
    }

    @Override
    public int calculateDicePoolSize() {
        final int basicPoolSize = this.calculateBasicPoolSize();
        final int specialtyBonus = this.hasApplicableSpecialty() ? 1 : 0;
        final int untrainedSkillPenalty = this.calculateUntrainedSkillPenalty();

        return basicPoolSize + specialtyBonus + untrainedSkillPenalty;
    }

    protected boolean hasApplicableSpecialty() {
        final Set<SpecialtyType> applicableSpecialties = new HashSet<>(this.nonDefaultApplicableSpecialties);
        applicableSpecialties.addAll(this.getDefaultApplicableSpecialties());

        return applicableSpecialties.stream().anyMatch(
                specialty -> this.character.getSpecialties().contains(specialty)
        );
    }

    public abstract Set<SpecialtyType> getDefaultApplicableSpecialties();

    private int calculateUntrainedSkillPenalty() {
        if (this.hasSkillFamiliarity()) return 0;
        if (this.getCategories().contains(CategoryType.MENTAL)) return -3;
        return -1;
    }

    private boolean hasSkillFamiliarity() {
        return character.getSkill(this.getDrivingSkill()) > 0;
    }

    public abstract SkillType getDrivingSkill();

}
