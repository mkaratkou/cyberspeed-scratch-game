package ae.cyberspeed.scratch.domain.config.probabilities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardSymbolProbabilityValue {
    @JsonProperty(value = "A", required = true)
    private int a;
    @JsonProperty(value = "B", required = true)
    private int b;
    @JsonProperty(value = "C", required = true)
    private int c;
    @JsonProperty(value = "D", required = true)
    private int d;
    @JsonProperty(value = "E", required = true)
    private int e;
    @JsonProperty(value = "F", required = true)
    private int f;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }
}
