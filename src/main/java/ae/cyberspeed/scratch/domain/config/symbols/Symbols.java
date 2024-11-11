package ae.cyberspeed.scratch.domain.config.symbols;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Symbols {

    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";
    public static final String E = "E";
    public static final String F = "F";
    public static final String TEN_X = "10x";
    public static final String FIVE_X = "5x";
    public static final String PLUS_ONE_THOUSAND = "+1000";
    public static final String PLUS_FIVE_HUNDRED = "+500";
    public static final String MISS = "MISS";

    @JsonProperty(value = A, required = true)
    private BaseSymbol a;
    @JsonProperty(value = B, required = true)
    private BaseSymbol b;
    @JsonProperty(value = C, required = true)
    private BaseSymbol c;
    @JsonProperty(value = D, required = true)
    private BaseSymbol d;
    @JsonProperty(value = E, required = true)
    private BaseSymbol e;
    @JsonProperty(value = F, required = true)
    private BaseSymbol f;
    @JsonProperty(value = TEN_X, required = true)
    private BaseSymbol tenX;
    @JsonProperty(value = FIVE_X, required = true)
    private BaseSymbol fiveX;
    @JsonProperty(value = PLUS_ONE_THOUSAND, required = true)
    private BaseSymbol plusOneThousand;
    @JsonProperty(value = PLUS_FIVE_HUNDRED, required = true)
    private BaseSymbol plusFiveHundred;
    @JsonProperty(value = MISS, required = true)
    private BaseSymbol miss;

    public BaseSymbol getA() {
        return a;
    }

    public void setA(BaseSymbol a) {
        this.a = a;
    }

    public BaseSymbol getB() {
        return b;
    }

    public void setB(BaseSymbol b) {
        this.b = b;
    }

    public BaseSymbol getC() {
        return c;
    }

    public void setC(BaseSymbol c) {
        this.c = c;
    }

    public BaseSymbol getD() {
        return d;
    }

    public void setD(BaseSymbol d) {
        this.d = d;
    }

    public BaseSymbol getE() {
        return e;
    }

    public void setE(BaseSymbol e) {
        this.e = e;
    }

    public BaseSymbol getF() {
        return f;
    }

    public void setF(BaseSymbol f) {
        this.f = f;
    }

    public BaseSymbol getTenX() {
        return tenX;
    }

    public void setTenX(BaseSymbol tenX) {
        this.tenX = tenX;
    }

    public BaseSymbol getFiveX() {
        return fiveX;
    }

    public void setFiveX(BaseSymbol fiveX) {
        this.fiveX = fiveX;
    }

    public BaseSymbol getPlusOneThousand() {
        return plusOneThousand;
    }

    public void setPlusOneThousand(BaseSymbol plusOneThousand) {
        this.plusOneThousand = plusOneThousand;
    }

    public BaseSymbol getPlusFiveHundred() {
        return plusFiveHundred;
    }

    public void setPlusFiveHundred(BaseSymbol plusFiveHundred) {
        this.plusFiveHundred = plusFiveHundred;
    }

    public BaseSymbol getMiss() {
        return miss;
    }

    public void setMiss(BaseSymbol miss) {
        this.miss = miss;
    }
}
