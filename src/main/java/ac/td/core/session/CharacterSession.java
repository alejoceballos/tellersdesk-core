package ac.td.core.session;

import ac.td.core.character.BaseCharacter;
import ac.td.core.character.VirtueType;

public class CharacterSession {
    private final BaseCharacter character;
    private int appliedVirtues = 0;

    public CharacterSession(final BaseCharacter character) {
        this.character = character;
    }

    public CharacterSession incrementAppliedVirtues(final VirtueType virtueType) {
        if (character.getVirtue().equals(virtueType)) {
            this.appliedVirtues++;
        }
        return this;
    }

    public int getAppliedVirtues() {
        return appliedVirtues;
    }
}
