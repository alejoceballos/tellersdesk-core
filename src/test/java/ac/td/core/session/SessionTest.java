package ac.td.core.session;

import ac.td.core.character.BaseCharacter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

class SessionTest {

    @Test
    public void testBuilder_New() {
        Assertions.assertNotNull(Session.builder());
    }

    @Test
    public void testBuilder_build() throws SessionBuilderException {
        Assertions.assertNotNull(
                Session.builder()
                        .add(Mockito.mock(BaseCharacter.class))
                        .add(Mockito.mock(Random.class))
                        .build());
    }

    @Test
    public void testBuilder_buildWithoutCharacter() {
        Assertions.assertThrows(SessionCharacterException.class, () -> Session.builder().build());
    }

    @Test
    public void testBuilder_addNullCharacter() {
        Assertions.assertThrows(SessionCharacterException.class, () -> Session.builder().add((BaseCharacter) null));
    }

    @Test
    public void testBuilder_addNullRandomizer() {
        Assertions.assertThrows(SessionRandomizerException.class, () -> Session.builder().add((Random) null));
    }

    @Test
    public void testBuilder_alreadyBuilt() throws SessionBuilderException {
        Session.Builder builder = Session.builder()
                .add(Mockito.mock(BaseCharacter.class))
                .add(Mockito.mock(Random.class));
        builder.build();
        Assertions.assertThrows(SessionAlreadyBuiltException.class, builder::build);
    }

    @Test
    public void testBuilder_withoutRandomizer() {
        Assertions.assertThrows(
                SessionRandomizerException.class,
                () -> Session.builder().add(Mockito.mock(BaseCharacter.class)).build());
    }

    @Test
    public void testCorrectlyBuilt() throws SessionBuilderException {
        final BaseCharacter character = Mockito.mock(BaseCharacter.class);
        final Random randomizer = Mockito.mock(Random.class);
        final Session session = Session.builder().add(character).add(randomizer).build();

        Assertions.assertEquals(randomizer, session.getRandomizer());
        Assertions.assertNotNull(session.getCharacterSession(character));
    }

}