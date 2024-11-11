package ae.cyberspeed.scratch.core.combination;

import ae.cyberspeed.scratch.domain.Config;
import ae.cyberspeed.scratch.domain.config.wincombinations.SameSymbolsDirectionallyCombination;
import ae.cyberspeed.scratch.domain.config.wincombinations.WinCombinations;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static ae.cyberspeed.scratch.domain.config.wincombinations.WinCombinations.*;

public class SameSymbolsDirectionallyCombinationParser {

    public static Map<String, SameSymbolsDirectionallyCombination> parse(Config config) {
        Map<String, SameSymbolsDirectionallyCombination> results = new LinkedHashMap<>();
        WinCombinations winCombinations = config.getWinCombinations();

        Optional.ofNullable(winCombinations.getSameSymbolsHorizontallyCombination())
                .ifPresent(c -> results.put(SAME_SYMBOLS_HORIZONTALLY, c));

        Optional.ofNullable(winCombinations.getSameSymbolsVerticallyCombination())
                .ifPresent(c -> results.put(SAME_SYMBOLS_VERTICALLY, c));

        Optional.ofNullable(winCombinations.getSameSymbolsDiagonallyLeftToRightCombination())
                .ifPresent(c -> results.put(SAME_SYMBOLS_DIAGONALLY_LEFT_TO_RIGHT, c));

        Optional.ofNullable(winCombinations.getSameSymbolsDiagonallyRightToLeftCombination())
                .ifPresent(c -> results.put(SAME_SYMBOLS_DIAGONALLY_RIGHT_TO_LEFT, c));
        return results;
    }

}
