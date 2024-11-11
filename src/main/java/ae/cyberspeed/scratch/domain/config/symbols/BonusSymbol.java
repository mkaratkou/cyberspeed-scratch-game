package ae.cyberspeed.scratch.domain.config.symbols;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BonusSymbol extends BaseSymbol {

    public static final String MULTIPLY_REWARD = "multiply_reward";
    public static final String EXTRA_BONUS = "extra_bonus";

    @JsonProperty(value = "impact", required = true)
    private String impact;

    @JsonProperty("extra")
    private int extra;

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public int getExtra() {
        return extra;
    }

    public void setExtra(int extra) {
        this.extra = extra;
    }
}
