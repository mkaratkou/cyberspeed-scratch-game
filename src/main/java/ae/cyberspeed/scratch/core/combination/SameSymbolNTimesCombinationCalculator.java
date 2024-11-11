package ae.cyberspeed.scratch.core.combination;

import ae.cyberspeed.scratch.core.initialization.CombinationComputation;
import ae.cyberspeed.scratch.domain.config.symbols.BaseSymbol;
import ae.cyberspeed.scratch.domain.config.wincombinations.SameSymbolNTimesCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class SameSymbolNTimesCombinationCalculator {

    private static final Logger logger = LoggerFactory.getLogger(SameSymbolNTimesCombinationCalculator.class);

    private final Map<String, SameSymbolNTimesCombination> combinationsMap;
    private final Map<Integer, String> nTimesToCombinationNameMap;

    public SameSymbolNTimesCombinationCalculator(Map<String, SameSymbolNTimesCombination> combinations) {
        this.combinationsMap = combinations;
        if (combinationsMap != null) {
            nTimesToCombinationNameMap = combinationsMap.entrySet().stream()
                    .collect(Collectors.toMap(e -> e.getValue().getCount(), Map.Entry::getKey));
        } else {
            nTimesToCombinationNameMap = Collections.emptyMap();
        }
    }

    public List<CombinationComputation> calculateReward(BaseSymbol[][] matrix) {
        Map<BaseSymbol, Integer> symbolFrequencyMap = new HashMap<>();
        List<CombinationComputation> results = new ArrayList<>();
        if (this.combinationsMap != null) {
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix[i].length; ++j) {
                    symbolFrequencyMap.compute(matrix[i][j], (k, v) -> v == null ? 1 : v + 1);
                }
            }
            symbolFrequencyMap.forEach((symbol, frequency) -> {
                String combinationName = nTimesToCombinationNameMap.get(frequency);
                if (combinationName != null) {
                    SameSymbolNTimesCombination combination = combinationsMap.get(combinationName);
                    logger.debug("Winning combination={} found for symbol={}", combinationName,
                            symbol.getSymbolValue());
                    results.add(new CombinationComputation(combinationName, symbol, combination.getRewardMultiplier()));
                }
            });
        }
        return results;
    }
}
