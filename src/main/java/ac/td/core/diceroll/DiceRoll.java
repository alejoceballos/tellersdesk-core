package ac.td.core.diceroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DiceRoll {
    private final DieFactory dieFactory;
    private final List<DiceSet> diceSets = new ArrayList<>();
    private DiceReRollType diceReRollType = DiceReRollType.AGAIN_10;
    private Integer dicePoolSize = 1;
    private boolean chanceRoll = true;

    public DiceRoll(final DieFactory dieFactory) throws DiceRollException {
        if (dieFactory == null) {
            throw new DiceRollException("Die factory cannot be null");
        }

        this.dieFactory = dieFactory;
    }

    public DiceReRollType getDiceReRollType() {
        return diceReRollType;
    }

    public Integer getDicePoolSize() {
        return dicePoolSize;
    }

    public boolean isChanceRoll() {
        return chanceRoll;
    }

    public DiceRoll setReRollType(final DiceReRollType diceReRollType) {
        this.diceReRollType = diceReRollType == null ? this.diceReRollType : diceReRollType;
        return this;
    }

    public DiceRoll setDicePoolSize(final int dicePoolSize) {
        if (dicePoolSize > 0) {
            this.dicePoolSize = dicePoolSize;
            this.chanceRoll = false;
        } else {
            this.dicePoolSize = 1;
            this.chanceRoll = true;
        }

        return this;
    }

    public DiceRoll perform() throws DiceRollException{
        final DiceRollAnalysisType lastRoll = analyze();

        if (DiceRollAnalysisType.NO_ROLL_PERFORMED.equals(lastRoll)) {
            this.diceSets.add(create().roll());
            return this;
        }

        if (DiceRollAnalysisType.SUCCESS_RE_ROLL_NEEDED.equals(lastRoll)) {
            final int reRollDicePool = this.calculateReRollDicePool(this.diceSets.size() - 1);
            this.diceSets.add(create(reRollDicePool).roll());
        }

        return this;
    }

    private DiceSet create() throws DiceSetException {
        return create(this.dicePoolSize);
    }

    private DiceSet create(final int dicePoolSize) throws DiceSetException {
        final Set<Die> dicePool = this.dieFactory.create(dicePoolSize);
        return new DiceSet(dicePool);
    }

    private int calculateReRollDicePool(final int diceSetIndex) {
        if (chanceRoll) {
            return 0;
        }

        return (int) this.diceSets.get(diceSetIndex).getLastResult().stream()
                .filter(result -> result >= this.diceReRollType.getValue())
                .count();
    }

    public DiceRollAnalysisType analyze() throws DiceRollAnalysisException {
        return this.analyze(this.diceSets.size());
    }

    public DiceRollAnalysisType analyze(final int roll) throws DiceRollAnalysisException {
        if (this.diceSets.isEmpty()) {
            return DiceRollAnalysisType.NO_ROLL_PERFORMED;
        }

        final int diceSetIndex = roll - 1;

        if (diceSetIndex < 0) {
            throw new DiceRollAnalysisException(DiceRollAnalysisException.AnalysisExceptionType.INCORRECT_ROLL_NUMBER);
        }

        if (roll > this.diceSets.size()) {
            throw new DiceRollAnalysisException(
                    DiceRollAnalysisException.AnalysisExceptionType.NON_EXISTENT_ROLL_SELECTED);
        }

        if (this.calculateReRollDicePool(diceSetIndex) > 0) {
            return DiceRollAnalysisType.SUCCESS_RE_ROLL_NEEDED;
        }

        return DiceRollAnalysisType.COMPLETED_ROLL;
    }

    public DiceRollStatusType status() throws DiceRollAnalysisException {
        final DiceRollAnalysisType lastRollAnalysis = analyze();

        if (!DiceRollAnalysisType.COMPLETED_ROLL.equals(lastRollAnalysis)) {
            return DiceRollStatusType.ON_GOING;
        }

        return chanceRoll ? this.chanceRollStatus() : this.normalStatus();
    }

    private DiceRollStatusType chanceRollStatus() {
        int result = this.diceSets.get(0).getLastResult().get(0);

        switch (result) {
            case 1: return DiceRollStatusType.DRAMATIC_FAILURE;
            case 10: return DiceRollStatusType.SUCCESS;
        }

        return DiceRollStatusType.FAILURE;
    }

    private DiceRollStatusType normalStatus() {
        final Map<Boolean, List<Integer>> rollResults = this.diceSets.stream()
                .flatMap((diceSet) -> diceSet.getLastResult().stream())
                .collect(Collectors.partitioningBy(roll -> roll >= 8));

        final List<Integer> success = rollResults.get(true);

        if (success != null && success.size() > 0) {
            return success.size() >= 5 ? DiceRollStatusType.EXCEPTIONAL_SUCCESS : DiceRollStatusType.SUCCESS;
        }

        return DiceRollStatusType.FAILURE;
    }

    public List<Integer> result() {
        return this.diceSets.stream()
                .flatMap((diceSet) -> diceSet.getLastResult().stream())
                .collect(Collectors.toList());
    }
}
