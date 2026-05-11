package patterns;

import research.Researcher;
import research.ResearchPaper;

/**
 * Strategy pattern -- concrete: report a researcher's papers and h-index.
 */
public class ResearchReportStrategy implements ReportStrategy {
    private Researcher researcher;
    private String label;

    public ResearchReportStrategy(Researcher researcher, String label) {
        this.researcher = researcher;
        this.label = label;
    }

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Research report for ").append(label).append(" ===\n");
        sb.append("H-index: ").append(researcher.calculateHIndex()).append("\n");
        sb.append("Papers: ").append(researcher.getPapers().size()).append("\n");
        for (ResearchPaper p : researcher.getPapers()) {
            sb.append(" - ").append(p).append("\n");
        }
        sb.append("Projects: ").append(researcher.getProjects().size()).append("\n");
        return sb.toString();
    }
}
