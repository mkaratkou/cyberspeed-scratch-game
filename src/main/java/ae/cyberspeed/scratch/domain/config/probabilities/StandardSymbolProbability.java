package ae.cyberspeed.scratch.domain.config.probabilities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardSymbolProbability {

    @JsonProperty(value = "column", required = true)
    private int column;

    @JsonProperty(value = "row", required = true)
    private int row;

    @JsonProperty(value = "symbols", required = true)
    private StandardSymbolProbabilityValue symbols;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public StandardSymbolProbabilityValue getSymbols() {
        return symbols;
    }

    public void setSymbols(StandardSymbolProbabilityValue symbols) {
        this.symbols = symbols;
    }
}
