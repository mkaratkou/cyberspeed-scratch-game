package ae.cyberspeed.scratch.domain.config.wincombinations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WinCombinations {

    public static final String SAME_SYMBOLS_HORIZONTALLY = "same_symbols_horizontally";
    public static final String SAME_SYMBOLS_VERTICALLY = "same_symbols_vertically";
    public static final String SAME_SYMBOLS_DIAGONALLY_LEFT_TO_RIGHT = "same_symbols_diagonally_left_to_right";
    public static final String SAME_SYMBOLS_DIAGONALLY_RIGHT_TO_LEFT = "same_symbols_diagonally_right_to_left";

    // No sameSymbolNTimesCombinations, because they're loaded dynamically

    @JsonProperty(value = SAME_SYMBOLS_HORIZONTALLY)
    private SameSymbolsDirectionallyCombination sameSymbolsHorizontallyCombination;

    @JsonProperty(value = SAME_SYMBOLS_VERTICALLY)
    private SameSymbolsDirectionallyCombination sameSymbolsVerticallyCombination;

    @JsonProperty(value = SAME_SYMBOLS_DIAGONALLY_LEFT_TO_RIGHT)
    private SameSymbolsDirectionallyCombination sameSymbolsDiagonallyLeftToRightCombination;

    @JsonProperty(value = SAME_SYMBOLS_DIAGONALLY_RIGHT_TO_LEFT)
    private SameSymbolsDirectionallyCombination sameSymbolsDiagonallyRightToLeftCombination;

    public SameSymbolsDirectionallyCombination getSameSymbolsHorizontallyCombination() {
        return sameSymbolsHorizontallyCombination;
    }

    public void setSameSymbolsHorizontallyCombination(
            SameSymbolsDirectionallyCombination sameSymbolsHorizontallyCombination) {
        this.sameSymbolsHorizontallyCombination = sameSymbolsHorizontallyCombination;
    }

    public SameSymbolsDirectionallyCombination getSameSymbolsVerticallyCombination() {
        return sameSymbolsVerticallyCombination;
    }

    public void setSameSymbolsVerticallyCombination(
            SameSymbolsDirectionallyCombination sameSymbolsVerticallyCombination) {
        this.sameSymbolsVerticallyCombination = sameSymbolsVerticallyCombination;
    }

    public SameSymbolsDirectionallyCombination getSameSymbolsDiagonallyLeftToRightCombination() {
        return sameSymbolsDiagonallyLeftToRightCombination;
    }

    public void setSameSymbolsDiagonallyLeftToRightCombination(
            SameSymbolsDirectionallyCombination sameSymbolsDiagonallyLeftToRightCombination) {
        this.sameSymbolsDiagonallyLeftToRightCombination = sameSymbolsDiagonallyLeftToRightCombination;
    }

    public SameSymbolsDirectionallyCombination getSameSymbolsDiagonallyRightToLeftCombination() {
        return sameSymbolsDiagonallyRightToLeftCombination;
    }

    public void setSameSymbolsDiagonallyRightToLeftCombination(
            SameSymbolsDirectionallyCombination sameSymbolsDiagonallyRightToLeftCombination) {
        this.sameSymbolsDiagonallyRightToLeftCombination = sameSymbolsDiagonallyRightToLeftCombination;
    }

}
