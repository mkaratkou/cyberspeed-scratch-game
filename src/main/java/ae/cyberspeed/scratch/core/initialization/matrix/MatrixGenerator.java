package ae.cyberspeed.scratch.core.initialization.matrix;

import ae.cyberspeed.scratch.domain.Config;
import ae.cyberspeed.scratch.domain.config.symbols.BaseSymbol;
import ae.cyberspeed.scratch.domain.util.BaseSymbolResolver;

import java.util.concurrent.*;

public class MatrixGenerator {

    private final Config config;

    public MatrixGenerator(Config config) {
        this.config = config;
    }

    public BaseSymbol[][] generateMatrix() {
        BaseSymbol[][] matrix = new BaseSymbol[this.config.getRows()][this.config.getColumns()];
        StandardCellValueGenerator standardCellValueGenerator = new StandardCellValueGenerator();

        CompletableFuture[] initCellsCallbacks = config.getProbabilities().getStandardSymbols().stream()
                .map(p -> CompletableFuture.supplyAsync(() -> standardCellValueGenerator.generate(p))
                        .thenAccept(symbolStr -> {
                            BaseSymbol baseSymbol = BaseSymbolResolver.resolve(symbolStr, config.getSymbols());
                            if (baseSymbol == null) {
                                throw new IllegalStateException("Can't resolve symbol=" + symbolStr);
                            }
                            baseSymbol.setSymbolString(symbolStr);
                            matrix[p.getRow()][p.getColumn()] = baseSymbol;
                        }))
                .toArray(CompletableFuture[]::new);
        // wait for all of them to complete
        CompletableFuture.allOf(initCellsCallbacks).join();

        return matrix;
    }

}
