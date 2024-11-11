package ae.cyberspeed.scratch.core.combination;

import ae.cyberspeed.scratch.domain.config.wincombinations.SameSymbolNTimesCombination;
import ae.cyberspeed.scratch.domain.config.wincombinations.SameSymbolsDirectionallyCombination;

import java.io.IOException;
import java.util.Map;

public class CombinationRegistry {

    private final Map<String, SameSymbolNTimesCombination> sameSymbolNTimesCombinations;
    private final Map<String, SameSymbolsDirectionallyCombination> sameSymbolDirectionallyCombinations;

    public CombinationRegistry(Map<String, SameSymbolNTimesCombination> sameSymbolNTimesCombinations,
            Map<String, SameSymbolsDirectionallyCombination> sameSymbolDirectionallyCombinations) throws IOException {
        this.sameSymbolNTimesCombinations = sameSymbolNTimesCombinations;
        this.sameSymbolDirectionallyCombinations = sameSymbolDirectionallyCombinations;
    }

    public Map<String, SameSymbolNTimesCombination> getSameSymbolNTimesCombinations() {
        return sameSymbolNTimesCombinations;
    }

    public Map<String, SameSymbolsDirectionallyCombination> getSameSymbolDirectionallyCombinations() {
        return sameSymbolDirectionallyCombinations;
    }

}
