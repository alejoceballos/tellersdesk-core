package ac.td.core.action.task;

import ac.td.core.action.task.skill.drive.Drive;
import ac.td.core.action.task.skill.drive.TaskVehicleException;
import ac.td.core.action.task.skill.drive.VehicleModelType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;

import java.util.Objects;

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

    public Drive createDriveTask(final VehicleModelType vehicle)
            throws TaskDieFactoryException, TaskCharacterException, TaskVehicleException {
        return new Drive(this.character, this.dieFactory, vehicle);
    }
}
