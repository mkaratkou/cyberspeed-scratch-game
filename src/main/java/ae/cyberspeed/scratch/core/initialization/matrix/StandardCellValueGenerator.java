package ae.cyberspeed.scratch.core.initialization.matrix;

import ae.cyberspeed.scratch.domain.config.probabilities.StandardSymbolProbability;
import ae.cyberspeed.scratch.domain.config.probabilities.StandardSymbolProbabilityValue;

import java.util.*;

import static ae.cyberspeed.scratch.domain.config.symbols.Symbols.*;

public class StandardCellValueGenerator {

    public String generate(StandardSymbolProbability probability) {
        StandardSymbolProbabilityValue symbols = probability.getSymbols();

        Map<String, ProbabilityInterval> probabilityMap = new HashMap<>();

        long probabilitySum = symbols.getA() + symbols.getB() + symbols.getC() + symbols.getD() + symbols.getE()
                + symbols.getF();

        double sum = 0.;
        probabilityMap.put(A, new ProbabilityInterval(sum, sum += 1. * symbols.getA() / probabilitySum));
        probabilityMap.put(B, new ProbabilityInterval(sum, sum += 1. * symbols.getB() / probabilitySum));
        probabilityMap.put(C, new ProbabilityInterval(sum, sum += 1. * symbols.getC() / probabilitySum));
        probabilityMap.put(D, new ProbabilityInterval(sum, sum += 1. * symbols.getD() / probabilitySum));
        probabilityMap.put(E, new ProbabilityInterval(sum, sum += 1. * symbols.getE() / probabilitySum));
        probabilityMap.put(F, new ProbabilityInterval(sum, sum += 1. * symbols.getF() / probabilitySum));

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
