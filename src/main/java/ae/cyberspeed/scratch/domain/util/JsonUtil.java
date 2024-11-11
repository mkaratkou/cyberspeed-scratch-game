package ae.cyberspeed.scratch.domain.util;

import ae.cyberspeed.scratch.domain.config.symbols.BonusSymbol;
import ae.cyberspeed.scratch.domain.config.symbols.StandardSymbol;
import ae.cyberspeed.scratch.domain.config.wincombinations.SameSymbolNTimesCombination;
import ae.cyberspeed.scratch.domain.config.wincombinations.SameSymbolsDirectionallyCombination;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public final class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .enable(SerializationFeature.INDENT_OUTPUT);

    static {
        objectMapper.registerSubtypes(StandardSymbol.class, BonusSymbol.class, SameSymbolNTimesCombination.class,
                SameSymbolsDirectionallyCombination.class);
    }

    public static <T> T readValue(File src, Class<T> valueType) throws IOException {
        return objectMapper.readValue(src, valueType);
    }

    public static JsonNode readRootNode(File src) throws IOException {
        return objectMapper.readTree(src);
    }

    public static String writeValueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonUtil() {
        // prevent instantiation
    }

}
