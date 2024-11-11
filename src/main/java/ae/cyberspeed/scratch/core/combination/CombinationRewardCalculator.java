package ae.cyberspeed.scratch.core.combination;

import ae.cyberspeed.scratch.domain.config.symbols.BaseSymbol;

public interface CombinationRewardCalculator {

    double calculateReward(BaseSymbol[][] matrix, BaseSymbol symbol);

    String getCombinationName();

}
