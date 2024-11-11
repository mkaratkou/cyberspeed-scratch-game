package ae.cyberspeed.scratch.domain.config.probabilities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Probabilities {

    @JsonProperty(value = "standard_symbols", required = true)
    private List<StandardSymbolProbability> standardSymbols;
    @JsonProperty(value = "bonus_symbols", required = true)
    private BonusSymbols bonusSymbols;

    public List<StandardSymbolProbability> getStandardSymbols() {
        return standardSymbols;
    }

    public void setStandardSymbols(List<StandardSymbolProbability> standardSymbols) {
        this.standardSymbols = standardSymbols;
    }

    public BonusSymbols getBonusSymbols() {
        return bonusSymbols;
    }

    public void setBonusSymbols(BonusSymbols bonusSymbols) {
        this.bonusSymbols = bonusSymbols;
    }
}
