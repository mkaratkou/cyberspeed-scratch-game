package ae.cyberspeed.scratch.domain.config.wincombinations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SameSymbolNTimesCombination extends BaseWinCombination {

    public static final String COUNT = "count";
    @JsonProperty(COUNT)
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
