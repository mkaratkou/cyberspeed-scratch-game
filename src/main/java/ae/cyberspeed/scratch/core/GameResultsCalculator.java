package ae.cyberspeed.scratch.core;

import ae.cyberspeed.scratch.core.combination.CombinationRegistry;
import ae.cyberspeed.scratch.core.combination.SameSymbolNTimesCombinationCalculator;
import ae.cyberspeed.scratch.core.combination.SameSymbolsDirectionallyCombinationCalculator;
import ae.cyberspeed.scratch.core.initialization.CombinationComputation;
import ae.cyberspeed.scratch.domain.Config;
import ae.cyberspeed.scratch.domain.GameResults;
import ae.cyberspeed.scratch.domain.config.symbols.BaseSymbol;
import ae.cyberspeed.scratch.domain.config.symbols.BonusSymbol;
import ae.cyberspeed.scratch.domain.config.symbols.Symbols;
import ae.cyberspeed.scratch.domain.config.wincombinations.WinCombinations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class GameResultsCalculator {

    private static final Logger logger = LoggerFactory.getLogger(GameResultsCalculator.class);

    private final CombinationRegistry combinationRegistry;

    public GameResultsCalculator(CombinationRegistry combinationRegistry) {
        this.combinationRegistry = combinationRegistry;
    }

    public GameResults calculate(BaseSymbol[][] matrix, Config config, double betAmount, BonusSymbol bonusSymbol)
            throws ExecutionException, InterruptedException {

        SameSymbolNTimesCombinationCalculator sameSymbolNTimesCombinationCalculator = new SameSymbolNTimesCombinationCalculator(
                combinationRegistry.getSameSymbolNTimesCombinations());

        WinCombinations winCombinations = config.getWinCombinations();

        CompletableFuture<List<CombinationComputation>> sameSymbolNTimesComputations = CompletableFuture
                .supplyAsync(() -> sameSymbolNTimesCombinationCalculator.calculateReward(matrix));

        List<CompletableFuture<List<CombinationComputation>>> sameSymbolDirectionallyComputations = Stream.of(
                new SameSymbolsDirectionallyCombinationCalculator(WinCombinations.SAME_SYMBOLS_HORIZONTALLY,
                        winCombinations.getSameSymbolsHorizontallyCombination()),
                new SameSymbolsDirectionallyCombinationCalculator(WinCombinations.SAME_SYMBOLS_VERTICALLY,
                        winCombinations.getSameSymbolsVerticallyCombination()),
                new SameSymbolsDirectionallyCombinationCalculator(WinCombinations.SAME_SYMBOLS_DIAGONALLY_LEFT_TO_RIGHT,
                        winCombinations.getSameSymbolsDiagonallyLeftToRightCombination()),
                new SameSymbolsDirectionallyCombinationCalculator(WinCombinations.SAME_SYMBOLS_DIAGONALLY_RIGHT_TO_LEFT,
                        winCombinations.getSameSymbolsDiagonallyRightToLeftCombination()))
                .map(c -> CompletableFuture.supplyAsync(() -> c.calculateReward(matrix))).collect(toList());

        List<CompletableFuture<?>> combinationTasks = new ArrayList<>();
        combinationTasks.add(sameSymbolNTimesComputations);
        combinationTasks.addAll(sameSymbolDirectionallyComputations);
        // await computations
        CompletableFuture.allOf(combinationTasks.toArray(new CompletableFuture[0])).join();

        List<CombinationComputation> computationResults = new ArrayList<>(sameSymbolNTimesComputations.get());
        for (CompletableFuture<List<CombinationComputation>> computation : sameSymbolDirectionallyComputations) {
            List<CombinationComputation> combinationComputations = computation.get();
            if (combinationComputations != null) {
                computationResults.addAll(combinationComputations);
            }
        }
        return toGameResults(matrix, betAmount, bonusSymbol, computationResults);
    }

    private GameResults toGameResults(BaseSymbol[][] matrix, double betAmount, BonusSymbol bonusSymbol,
            List<CombinationComputation> computationResults) {
        Map<BaseSymbol, List<CombinationComputation>> resultsBySymbol = computationResults.stream()
                .collect(groupingBy(CombinationComputation::getSymbol));

        GameResults results = new GameResults();
        results.setMatrix(Stream.of(matrix).map(a -> Stream.of(a).map(BaseSymbol::getSymbolValue).collect(toList()))
                .collect(toList()));
        results.setReward(calculateReward(betAmount, bonusSymbol, resultsBySymbol));

        results.setAppliedWinningCombinations(toWinningCombinations(resultsBySymbol));
        results.setAppliedBonusSymbol(bonusSymbol != null && !Objects.equals(bonusSymbol.getSymbolValue(), Symbols.MISS)
                ? bonusSymbol.getSymbolValue() : null);
        return results;
    }

    private double calculateReward(double betAmount, BonusSymbol bonusSymbol,
            Map<BaseSymbol, List<CombinationComputation>> resultsBySymbol) {
        double reward = 0.;
        for (Map.Entry<BaseSymbol, List<CombinationComputation>> entry : resultsBySymbol.entrySet()) {
            BaseSymbol symbol = entry.getKey();
            List<CombinationComputation> results = entry.getValue();
            double localReward = symbol.getRewardMultiplier();
            logger.debug("Processing symbol={}; symbol rewardMultiplier={}", symbol.getSymbolValue(),
                    symbol.getRewardMultiplier());
            for (CombinationComputation r : results) {
                localReward *= r.getReward();
                logger.debug("Adding points for symbol={}, combination={}, combination reward={}, localReward={}",
                        symbol.getSymbolValue(), r.getCombinationName(), r.getReward(), localReward);
            }
            reward += localReward;
            logger.debug("Symbol={} added {} points in total; reward={}", symbol.getSymbolValue(), localReward, reward);
        }
        reward *= betAmount;
        logger.debug("Applying betAmount={}, reward={}", betAmount, reward);
        if (bonusSymbol != null && reward > 0.0) {
            if (BonusSymbol.MULTIPLY_REWARD.equals(bonusSymbol.getImpact())) {
                reward *= bonusSymbol.getRewardMultiplier();
                logger.debug("Multiplied reward for bonus symbol={}, symbolMultiplier={}, final reward={}",
                        bonusSymbol.getSymbolValue(), bonusSymbol.getRewardMultiplier(), reward);
            } else if (BonusSymbol.EXTRA_BONUS.equals(bonusSymbol.getImpact())) {
                reward += bonusSymbol.getExtra();
                logger.debug("Incremented reward for bonus symbol={}, bonus extra={}, reward={}",
                        bonusSymbol.getSymbolValue(), bonusSymbol.getExtra(), reward);
            }
        }
        return reward;
    }

    private Map<String, List<String>> toWinningCombinations(
            Map<BaseSymbol, List<CombinationComputation>> resultsBySymbol) {
        Map<String, List<String>> results = new TreeMap<>();
        resultsBySymbol.forEach((k, v) -> results.put(k.getSymbolValue(),
                v.stream().map(CombinationComputation::getCombinationName).sorted().collect(toList())));
        return results;
    }

}
