package ae.cyberspeed.scratch.domain.config.wincombinations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "when", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = SameSymbolNTimesCombination.class, name = "same_symbols"),
        @JsonSubTypes.Type(value = SameSymbolsDirectionallyCombination.class, name = "linear_symbols") })
public abstract class BaseWinCombination {

    public static final String SAME_SYMBOLS = "same_symbols";
    public static final String WHEN = "when";
    public static final String GROUP = "group";
    public static final String REWARD_MULTIPLIER = "reward_multiplier";

    @JsonProperty(REWARD_MULTIPLIER)
    private double rewardMultiplier;
    @JsonProperty(WHEN)
    private String when;
    @JsonProperty(GROUP)
    private String group;

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
