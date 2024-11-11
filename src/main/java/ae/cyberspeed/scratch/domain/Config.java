package ae.cyberspeed.scratch.domain;

import ae.cyberspeed.scratch.domain.config.probabilities.Probabilities;
import ae.cyberspeed.scratch.domain.config.symbols.Symbols;
import ae.cyberspeed.scratch.domain.config.wincombinations.WinCombinations;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Config {
    @JsonProperty(value = "columns")
    private int columns;
    @JsonProperty("rows")
    private int rows;
    @JsonProperty(value = "symbols", required = true)
    private Symbols symbols;
    @JsonProperty(value = "probabilities", required = true)
    private Probabilities probabilities;
    @JsonProperty(value = "win_combinations", required = true)
    private WinCombinations winCombinations;

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Symbols getSymbols() {
        return symbols;
    }

    public void setSymbols(Symbols symbols) {
        this.symbols = symbols;
    }

    public Probabilities getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(Probabilities probabilities) {
        this.probabilities = probabilities;
    }

    public WinCombinations getWinCombinations() {
        return winCombinations;
    }

    public void setWinCombinations(WinCombinations winCombinations) {
        this.winCombinations = winCombinations;
    }
}
