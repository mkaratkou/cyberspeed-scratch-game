package ae.cyberspeed.scratch.domain.config.wincombinations;

import ae.cyberspeed.scratch.domain.util.Coordinate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class SameSymbolsDirectionallyCombination extends BaseWinCombination {

    @JsonProperty("covered_areas")
    @JsonDeserialize(using = CoveredAreasDeserializer.class)
    private List<List<Coordinate>> coveredAreas;

    public List<List<Coordinate>> getCoveredAreas() {
        return coveredAreas;
    }

    public void setCoveredAreas(List<List<Coordinate>> coveredAreas) {
        this.coveredAreas = coveredAreas;
    }
}
