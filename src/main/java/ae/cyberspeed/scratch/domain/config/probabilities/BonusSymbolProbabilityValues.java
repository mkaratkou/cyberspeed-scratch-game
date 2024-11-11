package ae.cyberspeed.scratch.domain.config.probabilities;

import com.fasterxml.jackson.annotation.JsonProperty;

import static ae.cyberspeed.scratch.domain.config.symbols.Symbols.*;

public class BonusSymbolProbabilityValues {

    @JsonProperty(value = TEN_X, required = true)
    private int tenX;

    @JsonProperty(value = FIVE_X, required = true)
    private int fiveX;

    @JsonProperty(value = PLUS_ONE_THOUSAND, required = true)
    private int plusOneThousand;

    @JsonProperty(value = PLUS_FIVE_HUNDRED, required = true)
    private int plusFiveHundred;

    @JsonProperty(value = MISS, required = true)
    private int miss;

    public int getTenX() {
        return tenX;
    }

    public void setTenX(int tenX) {
        this.tenX = tenX;
    }

    public int getFiveX() {
        return fiveX;
    }

    public void setFiveX(int fiveX) {
        this.fiveX = fiveX;
    }

    public int getPlusOneThousand() {
        return plusOneThousand;
    }

    public void setPlusOneThousand(int plusOneThousand) {
        this.plusOneThousand = plusOneThousand;
    }

    public int getPlusFiveHundred() {
        return plusFiveHundred;
    }

    public void setPlusFiveHundred(int plusFiveHundred) {
        this.plusFiveHundred = plusFiveHundred;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }
}
