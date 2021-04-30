package ac.td.core.action.task;

import ac.td.core.action.task.skill.drive.TaskVehicleException;
import ac.td.core.action.task.skill.drive.VehicleModelType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DieFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    public void createDriveTask_VehicleNull() {
        Assertions.assertThrows(
                TaskVehicleException.class,
                () -> new TaskFactory(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class))
                        .createDriveTask(null));
    }

    @Test
    public void createDriveTask() throws TaskException {
        Assertions.assertNotNull(
                new TaskFactory(Mockito.mock(SkillfulCharacter.class), Mockito.mock(DieFactory.class))
                .createDriveTask(VehicleModelType.COMPACT_CAR));
    }

}
