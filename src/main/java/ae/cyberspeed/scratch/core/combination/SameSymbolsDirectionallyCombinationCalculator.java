package ae.cyberspeed.scratch.core.combination;

import ae.cyberspeed.scratch.core.initialization.CombinationComputation;
import ae.cyberspeed.scratch.domain.config.symbols.BaseSymbol;
import ae.cyberspeed.scratch.domain.config.wincombinations.SameSymbolsDirectionallyCombination;
import ae.cyberspeed.scratch.domain.util.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SameSymbolsDirectionallyCombinationCalculator {

    private static final Logger logger = LoggerFactory.getLogger(SameSymbolsDirectionallyCombinationCalculator.class);
    private final String combinationName;
    private final SameSymbolsDirectionallyCombination combination;

    public SameSymbolsDirectionallyCombinationCalculator(String combinationName,
            SameSymbolsDirectionallyCombination combination) {
        this.combinationName = combinationName;
        this.combination = combination;
    }

    public List<CombinationComputation> calculateReward(BaseSymbol[][] matrix) {
        return combination.getCoveredAreas().stream().map(a -> calculateReward(a, matrix)).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private CombinationComputation calculateReward(List<Coordinate> coveredArea, BaseSymbol[][] matrix) {

        if (!coveredArea.isEmpty()) {
            Coordinate firstCoordinate = coveredArea.get(0);
            BaseSymbol referenceSymbol = matrix[firstCoordinate.getRow()][firstCoordinate.getColumn()];
            String referenceSymbolStr = referenceSymbol.getSymbolValue();
            boolean allTheSame = coveredArea.stream().skip(1)
                    .allMatch(c -> referenceSymbolStr.equals(matrix[c.getRow()][c.getColumn()].getSymbolValue()));
            if (allTheSame) {
                logger.debug("Winning combination={} found for area={}", combinationName, coveredArea);
                return new CombinationComputation(combinationName, referenceSymbol, combination.getRewardMultiplier());
            }
        }
        return null;
    }

}
