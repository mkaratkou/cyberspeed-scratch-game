package ae.cyberspeed.scratch.core.initialization;

import ae.cyberspeed.scratch.domain.config.symbols.BaseSymbol;

public class CombinationComputation {

    private final String combinationName;
    private final BaseSymbol symbol;
    private final double reward;

    public CombinationComputation(String combinationName, BaseSymbol symbol, double reward) {
        this.combinationName = combinationName;
        this.symbol = symbol;
        this.reward = reward;
    }

    public String getCombinationName() {
        return combinationName;
    }

    public BaseSymbol getSymbol() {
        return symbol;
    }

    public double getReward() {
        return reward;
    }
}
