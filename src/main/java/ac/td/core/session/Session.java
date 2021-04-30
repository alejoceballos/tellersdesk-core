package ac.td.core.session;

import ac.td.core.character.BaseCharacter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Session {
    private Random randomizer;
    private Map<BaseCharacter, CharacterSession> charactersSession = new HashMap<>();

    public CharacterSession getCharacterSession(final BaseCharacter character) {
        return this.charactersSession.get(character);
    }

    public Random getRandomizer() {
        return randomizer;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Session subject = new Session();

        public Builder add(final BaseCharacter character) throws SessionCharacterException {
            if (character == null) {
                throw new SessionCharacterException("Session character cannot be null");
            }

            this.subject.charactersSession.put(character, new CharacterSession(character));
            return this;
        }

        public Builder add(final Random randomizer) throws SessionRandomizerException {
            if (randomizer == null) {
                throw new SessionRandomizerException("Randomizer cannot be null");
            }

            this.subject.randomizer = randomizer;
            return this;
        }

        public Session build()
                throws SessionCharacterException, SessionAlreadyBuiltException, SessionRandomizerException {

            if (this.subject == null) {
                throw new SessionAlreadyBuiltException("Character already built");
            }

            if (this.subject.charactersSession.size() == 0) {
                throw new SessionCharacterException("No characters added to session");
            }

            if (this.subject.randomizer == null) {
                throw new SessionRandomizerException("Randomizer is needed for dice rolls");
            }

            final Session session = this.subject;
            this.subject = null;

            return session;
        }
    }
}
