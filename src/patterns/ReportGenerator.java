package patterns;

/**
 * Strategy pattern -- the "Context". Holds a strategy and delegates to it.
 * The strategy can be swapped at runtime.
 */
public class ReportGenerator {
    private ReportStrategy strategy;

    public ReportGenerator(ReportStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(ReportStrategy strategy) {
        this.strategy = strategy;
    }

    public String generate() {
        if (strategy == null) {
            return "(no strategy set)";
        }
        return strategy.generate();
    }
}
