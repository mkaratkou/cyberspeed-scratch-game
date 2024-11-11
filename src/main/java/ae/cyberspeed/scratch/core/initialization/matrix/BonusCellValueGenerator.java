package ae.cyberspeed.scratch.core.initialization.matrix;

import ae.cyberspeed.scratch.domain.config.probabilities.BonusSymbolProbabilityValues;
import ae.cyberspeed.scratch.domain.config.probabilities.BonusSymbols;

import java.util.HashMap;
import java.util.Map;

import static ae.cyberspeed.scratch.domain.config.symbols.Symbols.*;

public class BonusCellValueGenerator {

    public String generate(BonusSymbols bonusSymbols) {
        BonusSymbolProbabilityValues symbols = bonusSymbols.getSymbols();

        Map<String, ProbabilityInterval> probabilityMap = new HashMap<>();

        long probabilitySum = symbols.getTenX() + symbols.getFiveX() + symbols.getPlusOneThousand()
                + symbols.getPlusFiveHundred() + symbols.getMiss();

        double sum = 0.;
        probabilityMap.put(TEN_X, new ProbabilityInterval(sum, sum += 1. * symbols.getTenX() / probabilitySum));
        probabilityMap.put(FIVE_X, new ProbabilityInterval(sum, sum += 1. * symbols.getFiveX() / probabilitySum));
        probabilityMap.put(PLUS_ONE_THOUSAND,
                new ProbabilityInterval(sum, sum += 1. * symbols.getPlusOneThousand() / probabilitySum));
        probabilityMap.put(PLUS_FIVE_HUNDRED,
                new ProbabilityInterval(sum, sum += 1. * symbols.getPlusFiveHundred() / probabilitySum));
        probabilityMap.put(MISS, new ProbabilityInterval(sum, sum += 1. * symbols.getMiss() / probabilitySum));

        double randomValue = Math.random();
        for (Map.Entry<String, ProbabilityInterval> e : probabilityMap.entrySet()) {
            if (e.getValue().contains(randomValue)) {
                return e.getKey();
            }
        }
        // should never happen, but leaving as a sanity check
        throw new IllegalStateException("No interval found for random value=" + randomValue);

    }

}
