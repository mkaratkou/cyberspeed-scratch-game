package ae.cyberspeed.scratch.core.combination;

import ae.cyberspeed.scratch.domain.config.wincombinations.SameSymbolNTimesCombination;
import ae.cyberspeed.scratch.domain.util.JsonUtil;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static ae.cyberspeed.scratch.domain.config.wincombinations.BaseWinCombination.*;
import static ae.cyberspeed.scratch.domain.config.wincombinations.SameSymbolNTimesCombination.COUNT;

/**
 * In case matrix size is more than 3x3 we need to process same_symbol_10_times, same_symbol_11_times .. combinations as
 * well since they're dynamic. So, we have to do it in a single place, here.
 */
public class SameSymbolNTimesCombinationsParser {

    private static final JsonPointer COMBINATIONS_POINTER = JsonPointer.compile("/win_combinations");

    public static Map<String, SameSymbolNTimesCombination> parse(File gameConfiguration) throws IOException {
        JsonNode root = JsonUtil.readRootNode(gameConfiguration);
        JsonNode at = root.at(COMBINATIONS_POINTER);
        Iterator<String> combinationNodeIt = at.fieldNames();
        Map<String, SameSymbolNTimesCombination> results = new LinkedHashMap<>();
        while (combinationNodeIt.hasNext()) {
            String nodeName = combinationNodeIt.next();
            JsonNode combinationNode = at.get(nodeName);
            if (combinationNode.get(GROUP) != null && combinationNode.get(WHEN) != null
                    && SAME_SYMBOLS.equals(combinationNode.get(GROUP).asText())
                    && SAME_SYMBOLS.equals(combinationNode.get(WHEN).asText())) {
                SameSymbolNTimesCombination combination = new SameSymbolNTimesCombination();
                combination.setGroup(SAME_SYMBOLS);
                combination.setWhen(SAME_SYMBOLS);
                JsonNode rewardMultiplierNode = combinationNode.get(REWARD_MULTIPLIER);
                if (rewardMultiplierNode == null) {
                    throw new IllegalArgumentException(
                            "No 'reward_multiplier' value found for same_symbol winning combination");
                }
                combination.setRewardMultiplier(rewardMultiplierNode.asDouble());
                JsonNode countNode = combinationNode.get(COUNT);
                if (countNode == null) {
                    throw new IllegalArgumentException("No 'count' value found for same_symbol winning combination");
                }
                combination.setCount(countNode.asInt());

                results.put(nodeName, combination);
            }
        }
        return results;
    }
}
