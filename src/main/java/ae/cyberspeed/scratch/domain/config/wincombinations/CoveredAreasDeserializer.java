package ae.cyberspeed.scratch.domain.config.wincombinations;

import ae.cyberspeed.scratch.domain.util.Coordinate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoveredAreasDeserializer extends StdDeserializer<List<List<Coordinate>>> {

    public CoveredAreasDeserializer() {
        super(List.class);
    }

    protected CoveredAreasDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<List<Coordinate>> deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {

        String[][] values = jsonParser.readValueAs(String[][].class);
        if (values == null) {
            return Collections.emptyList();
        }
        List<List<Coordinate>> result = new ArrayList<>();
        for (String[] a : values) {
            List<Coordinate> row = new ArrayList<>();
            for (String s : a) {
                row.add(CoordinateParser.parse(s));
            }
            result.add(row);
        }
        return result;
    }
}
