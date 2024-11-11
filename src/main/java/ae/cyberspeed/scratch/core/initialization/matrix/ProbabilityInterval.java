package ae.cyberspeed.scratch.core.initialization.matrix;

public class ProbabilityInterval {

    public final double startInclusive;
    public final double endExclusive;

    public ProbabilityInterval(double startInclusive, double endExclusive) {
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
    }

    public boolean contains(double value) {
        return startInclusive <= value && value < endExclusive;
    }
}
