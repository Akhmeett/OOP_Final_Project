package patterns;

/**
 * Strategy pattern -- the algorithm contract.
 * Different concrete strategies generate different reports.
 */
public interface ReportStrategy {
    String generate();
}
