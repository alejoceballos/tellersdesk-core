package ac.td.core.session;

import ac.td.core.character.BaseCharacter;
import ac.td.core.character.VirtueType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CharacterSessionTest {

    @Test
    void testNew_Increment_GetAppliedVirtues() {
        final BaseCharacter character = Mockito.mock(BaseCharacter.class);
        Mockito.when(character.getVirtue()).thenReturn(VirtueType.CHARITY);

        Assertions.assertEquals(
                1,
                new CharacterSession(character)
                        .incrementAppliedVirtues(VirtueType.CHARITY)
                        .getAppliedVirtues());
    }
}