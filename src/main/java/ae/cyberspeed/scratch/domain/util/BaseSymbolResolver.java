package ae.cyberspeed.scratch.domain.util;

import ae.cyberspeed.scratch.domain.config.symbols.BaseSymbol;
import ae.cyberspeed.scratch.domain.config.symbols.Symbols;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class BaseSymbolResolver {

    private static final Map<String, Function<Symbols, BaseSymbol>> symbolToValueMap = new HashMap<>();

    static {
        symbolToValueMap.put(Symbols.A, Symbols::getA);
        symbolToValueMap.put(Symbols.B, Symbols::getB);
        symbolToValueMap.put(Symbols.C, Symbols::getC);
        symbolToValueMap.put(Symbols.D, Symbols::getD);
        symbolToValueMap.put(Symbols.E, Symbols::getE);
        symbolToValueMap.put(Symbols.F, Symbols::getF);
        symbolToValueMap.put(Symbols.TEN_X, Symbols::getTenX);
        symbolToValueMap.put(Symbols.FIVE_X, Symbols::getFiveX);
        symbolToValueMap.put(Symbols.PLUS_ONE_THOUSAND, Symbols::getPlusOneThousand);
        symbolToValueMap.put(Symbols.PLUS_FIVE_HUNDRED, Symbols::getPlusFiveHundred);
        symbolToValueMap.put(Symbols.MISS, Symbols::getMiss);
    }

    public static BaseSymbol resolve(String symbol, Symbols symbols) {
        Function<Symbols, BaseSymbol> symbolsBaseSymbolFunction = symbolToValueMap.get(symbol);
        if (symbolsBaseSymbolFunction == null) {
            return null;
        }
        return symbolsBaseSymbolFunction.apply(symbols);
    }

    private BaseSymbolResolver() {
        // prevent instantiation
    }

}
