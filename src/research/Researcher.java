package research;

import java.util.Comparator;
import java.util.List;

public interface Researcher {
    void printPapers(Comparator<ResearchPaper> c);
    int calculateHIndex();
    List<ResearchProject> getProjects();
    List<ResearchPaper> getPapers();
    void addPaper(ResearchPaper p);
    void joinProject(ResearchProject p);
}