package ac.td.core.action.task.skill.drive;

import ac.td.core.action.ActionType;
import ac.td.core.action.task.TaskCharacterException;
import ac.td.core.action.task.TaskDieFactoryException;
import ac.td.core.action.task.skill.SkillTask;
import ac.td.core.action.task.skill.WrongSpecialtySkillException;
import ac.td.core.character.AttributeType;
import ac.td.core.character.CategoryType;
import ac.td.core.skill.SkillType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;
import ac.td.core.skill.SpecialtyType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Drive extends SkillTask {
    private VehicleModelType vehicle;

    public Drive(final SkillfulCharacter character, final DieFactory dieFactory, final VehicleModelType vehicle)
            throws TaskDieFactoryException, TaskCharacterException, TaskVehicleException {

        super(character, dieFactory);
        this.setVehicle(vehicle);
    }

    public Drive(
            final SkillfulCharacter character,
            final DieFactory dieFactory,
            final VehicleModelType vehicle,
            final Set<SpecialtyType> nonDefaultApplicableSpecialties)
            throws TaskCharacterException, TaskDieFactoryException, TaskVehicleException, WrongSpecialtySkillException {

        super(character, dieFactory, nonDefaultApplicableSpecialties);
        this.setVehicle(vehicle);
    }

    private void setVehicle(final VehicleModelType vehicle) throws TaskVehicleException {
        if (Objects.isNull(vehicle)) {
            throw new TaskVehicleException("Vehicle cannot be null");
        }

        this.vehicle = vehicle;
    }

    @Override
    public int calculateBasicPoolSize() {
        final int attribute = this.character.getAttribute(AttributeType.DEXTERITY);
        final int skill = this.character.getSkill(this.getDrivingSkill());
        final int vehicle = this.vehicle.getVehicleHandling();
        final int modifiers = this.calculateModifiersDicePoolSize();

        return attribute + skill + vehicle + modifiers;
    }

    @Override
    public ActionType getType() {
        return ActionType.INSTANT;
    }

    @Override
    public Set<CategoryType> getCategories() {
        return Set.of(CategoryType.PHYSICAL);
    }

    @Override
    public Set<SpecialtyType> getDefaultApplicableSpecialties() {
        final Set<SpecialtyType> applicableSpecialties = new HashSet<>();

        if (this.vehicle.getType().equals(VehicleType.CAR)) applicableSpecialties.add(SpecialtyType.RACING);
        if (this.vehicle.getType().equals(VehicleType.MOTORCYCLE)) applicableSpecialties.add(SpecialtyType.MOTORCYCLES);
        if (this.vehicle.getType().equals(VehicleType.PLANE)) applicableSpecialties.add(SpecialtyType.RACING);
        if (this.vehicle.getType().equals(VehicleType.BOAT)) applicableSpecialties.add(SpecialtyType.NAVIGATING);

        return Set.of(SpecialtyType.EVASION, SpecialtyType.MOTORCYCLES, SpecialtyType.PILOTING, SpecialtyType.RACING);
    }

    @Override
    public SkillType getDrivingSkill() {
        return SkillType.DRIVE;
    }
}
