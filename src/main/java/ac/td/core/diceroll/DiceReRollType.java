package ac.td.core.diceroll;

public enum DiceReRollType {
    AGAIN_10(10), AGAIN_9(9), AGAIN_8(8);

    private int value;

    DiceReRollType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
