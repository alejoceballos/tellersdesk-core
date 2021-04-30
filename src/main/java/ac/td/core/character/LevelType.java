package ac.td.core.character;

public enum LevelType {
    BEGINNER(34),
    EXPERIENCED(69),
    SPECIALIST(109),
    HEROIC(134);

    private final int initialXp;

    LevelType(final int initialXp) {
        this.initialXp = initialXp;
    }

    public int getInitialXp() {
        return this.initialXp;
    }
}
