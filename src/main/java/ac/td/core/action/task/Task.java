package ac.td.core.action.task;

import ac.td.core.action.ActionType;
import ac.td.core.character.CategoryType;
import ac.td.core.character.SkillfulCharacter;
import ac.td.core.diceroll.DiceRoll;
import ac.td.core.diceroll.DiceRollAnalysisType;
import ac.td.core.diceroll.DiceRollException;
import ac.td.core.diceroll.DieFactory;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public abstract class Task {

    private final DieFactory dieFactory;
    private DiceRoll diceRoll;
    protected final SkillfulCharacter character;
    private int[] modifiersDicePool = new int[0];

    public Task(final SkillfulCharacter character, final DieFactory dieFactory)
            throws TaskCharacterException, TaskDieFactoryException {

        if (Objects.isNull(character)) {
            throw new TaskCharacterException("Character cannot be null");
        }

        if (Objects.isNull(dieFactory)) {
            throw new TaskDieFactoryException("Factory cannot be null");
        }

        this.character = character;
        this.dieFactory = dieFactory;
    }

    public DiceRoll getDiceRoll() {
        return diceRoll;
    }

    protected int calculateModifiersDicePoolSize() {
        return Arrays.stream(this.modifiersDicePool).sum();
    }

    @SuppressWarnings("unchecked")
    public <T extends Task> T setModifiersDice(final int ... modifier) throws TaskModifierException {
        if (modifier == null) {
            throw new TaskModifierException("Modifier cannot be null");
        }

        if (modifier.length == 0) {
            throw new TaskModifierException("Modifier list cannot be empty");
        }

        this.modifiersDicePool = modifier;

        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Task> T perform() throws DiceRollException {
        this.diceRoll = new DiceRoll(this.dieFactory).setDicePoolSize(this.calculateDicePoolSize());
        this.rollDiceUntilIsFinished(this.diceRoll);
        return (T) this;
    }

    private void rollDiceUntilIsFinished(final DiceRoll diceRoll) throws DiceRollException {
        if (diceRoll.perform().analyze().equals(DiceRollAnalysisType.COMPLETED_ROLL)) {
            return;
        }

        this.rollDiceUntilIsFinished(diceRoll);
    }

    public int calculateDicePoolSize() {
        return this.calculateBasicPoolSize();
    }

    public abstract int calculateBasicPoolSize();

    public abstract ActionType getType();

    public abstract Set<CategoryType> getCategories();

}
