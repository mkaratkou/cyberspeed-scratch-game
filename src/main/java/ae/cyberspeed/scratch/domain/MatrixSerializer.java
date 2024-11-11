package ae.cyberspeed.scratch.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.List;

public class MatrixSerializer extends StdSerializer<List<List<String>>> {

    protected MatrixSerializer() {
        this(null);
    }

    public MatrixSerializer(Class<List<List<String>>> t) {
        super(t);
    }

    @Override
    public void serialize(List<List<String>> matrix, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeStartArray();
        jsonGenerator.writeRaw("\n ");
        for (List<String> row : matrix) {

            jsonGenerator.writeStartArray();
            for (String s : row) {
                jsonGenerator.writeString(s);
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeRaw("\n");
        }
        jsonGenerator.writeEndArray();

    }

}
