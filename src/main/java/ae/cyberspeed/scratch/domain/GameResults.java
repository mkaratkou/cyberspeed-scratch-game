package ae.cyberspeed.scratch.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
import java.util.Map;

public class GameResults {

    @JsonSerialize(using = MatrixSerializer.class)
    @JsonProperty("matrix")
    private List<List<String>> matrix;
    @JsonProperty("reward")
    private double reward;
    @JsonProperty("applied_winning_combinations")
    private Map<String, List<String>> appliedWinningCombinations;
    @JsonProperty("applied_bonus_symbol")
    private String appliedBonusSymbol;

    public List<List<String>> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<List<String>> matrix) {
        this.matrix = matrix;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public Map<String, List<String>> getAppliedWinningCombinations() {
        return appliedWinningCombinations;
    }

    public void setAppliedWinningCombinations(Map<String, List<String>> appliedWinningCombinations) {
        this.appliedWinningCombinations = appliedWinningCombinations;
    }

    public String getAppliedBonusSymbol() {
        return appliedBonusSymbol;
    }

    public void setAppliedBonusSymbol(String appliedBonusSymbol) {
        this.appliedBonusSymbol = appliedBonusSymbol;
    }
}
