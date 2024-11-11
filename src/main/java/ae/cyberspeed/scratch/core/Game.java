package ae.cyberspeed.scratch.core;

import ae.cyberspeed.scratch.core.combination.CombinationRegistry;
import ae.cyberspeed.scratch.core.combination.SameSymbolNTimesCombinationsParser;
import ae.cyberspeed.scratch.core.combination.SameSymbolsDirectionallyCombinationParser;
import ae.cyberspeed.scratch.core.initialization.CliParser;
import ae.cyberspeed.scratch.core.initialization.ConfigFileResolver;
import ae.cyberspeed.scratch.core.initialization.matrix.BonusCellValueGenerator;
import ae.cyberspeed.scratch.core.initialization.matrix.MatrixGenerator;
import ae.cyberspeed.scratch.domain.Config;
import ae.cyberspeed.scratch.domain.GameResults;
import ae.cyberspeed.scratch.domain.config.symbols.BaseSymbol;
import ae.cyberspeed.scratch.domain.config.symbols.BonusSymbol;
import ae.cyberspeed.scratch.domain.util.BaseSymbolResolver;
import ae.cyberspeed.scratch.domain.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Game {

    public static final String CONFIG_PARAM = "--config";
    public static final String BETTING_AMOUNT_PARAM = "--betting-amount";
    public static final String EXPECTED_PARAMS_MESSAGE = "Expected both '--config' and '--betting-amount' parameters (case-sensitive) expected.";

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    public static void main(String[] args) {

        logger.info("The Game is starting");

        String configLocation = CliParser.parseAsString(args, CONFIG_PARAM);
        Integer betAmount = CliParser.parseAsInteger(args, BETTING_AMOUNT_PARAM);

        if (configLocation == null) {
            logger.error("No value provided for {} param. {}", CONFIG_PARAM, EXPECTED_PARAMS_MESSAGE);
            System.exit(1);
        }
        if (betAmount == null) {
            logger.error("No value provided for {} param. {}", BETTING_AMOUNT_PARAM, EXPECTED_PARAMS_MESSAGE);
            System.exit(1);
        }
        if (betAmount <= 0) {
            logger.error("Bet amount param ({}) must be positive, current value={}", BETTING_AMOUNT_PARAM, betAmount);
            System.exit(1);
        }
        try {
            File configFile = ConfigFileResolver.resolveConfigFile(configLocation);
            Config config = JsonUtil.readValue(configFile, Config.class);

            MatrixGenerator matrixGenerator = new MatrixGenerator(config);
            BaseSymbol[][] matrix = matrixGenerator.generateMatrix();
            // assuming 0..1 bonus symbol max for the game if fine
            BonusSymbol bonusSymbol = Math.random() > 0.5 ? createAndSetBonusSymbol(config, matrix) : null;

            // read dynamic & static combinations
            CombinationRegistry combinationRegistry = new CombinationRegistry(
                    SameSymbolNTimesCombinationsParser.parse(configFile),
                    SameSymbolsDirectionallyCombinationParser.parse(config));
            GameResultsCalculator resultsCalculator = new GameResultsCalculator(combinationRegistry);
            GameResults results = resultsCalculator.calculate(matrix, config, betAmount, bonusSymbol);
            logger.info(JsonUtil.writeValueAsString(results));

        } catch (IOException e) {
            logger.error("Unable to download config file={}, closing the app..", configLocation, e);
            System.exit(1);
        } catch (ExecutionException e) {
            logger.error("Error while executing winning combination", e);
            System.exit(1);
        } catch (InterruptedException e) {
            logger.error("Error while executing winning combination", e);
            Thread.currentThread().interrupt();
            System.exit(1);
        }
        logger.info("Game over. Bye!");
    }

    private static BonusSymbol createAndSetBonusSymbol(Config config, BaseSymbol[][] matrix) {
        Random random = new Random();
        int i = random.nextInt(config.getRows());
        int j = random.nextInt(config.getColumns());
        BonusCellValueGenerator bonusCellValueGenerator = new BonusCellValueGenerator();
        String bonusSymbolStr = bonusCellValueGenerator.generate(config.getProbabilities().getBonusSymbols());
        BonusSymbol bonusSymbol = (BonusSymbol) BaseSymbolResolver.resolve(bonusSymbolStr, config.getSymbols());
        if (bonusSymbol == null) {
            throw new IllegalStateException("Can't resolve symbol=" + bonusSymbolStr);
        }
        bonusSymbol.setSymbolString(bonusSymbolStr);
        matrix[i][j] = bonusSymbol;
        return bonusSymbol;
    }

}
