package ae.cyberspeed.scratch.domain.config.symbols;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = StandardSymbol.class, name = BaseSymbol.STANDARD_TYPE),
        @JsonSubTypes.Type(value = BonusSymbol.class, name = BaseSymbol.BONUS_TYPE) })
public class BaseSymbol implements Comparable<BaseSymbol> {

    public static final String STANDARD_TYPE = "standard";
    public static final String BONUS_TYPE = "bonus";

    @JsonIgnore
    private String symbolValue;
    @JsonProperty(value = "type", required = true)
    private String type;
    @JsonProperty(value = "reward_multiplier", required = true)
    private double rewardMultiplier;

    public String getSymbolValue() {
        return symbolValue;
    }

    public void setSymbolString(String symbolValue) {
        this.symbolValue = symbolValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof BaseSymbol) {
            return Objects.equals(this.symbolValue, ((BaseSymbol) other).symbolValue);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getSymbolValue() != null ? this.getSymbolValue().hashCode() : 0;
    }

    @Override
    public int compareTo(BaseSymbol o) {
        return this.getSymbolValue().compareTo(o.symbolValue);
    }
}
