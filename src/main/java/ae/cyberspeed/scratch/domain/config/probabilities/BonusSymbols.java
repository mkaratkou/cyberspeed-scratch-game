package ae.cyberspeed.scratch.domain.config.probabilities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BonusSymbols {
    @JsonProperty("symbols")
    private BonusSymbolProbabilityValues symbols;

    public BonusSymbolProbabilityValues getSymbols() {
        return symbols;
    }

    public void setSymbols(BonusSymbolProbabilityValues symbols) {
        this.symbols = symbols;
    }
}
