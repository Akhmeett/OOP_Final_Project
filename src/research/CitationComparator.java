package research;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Sort papers by citation count, DESCENDING (most-cited first).
 */
public class CitationComparator implements Comparator<ResearchPaper>, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public int compare(ResearchPaper a, ResearchPaper b) {
        return Integer.compare(b.getCitations(), a.getCitations());
    }
}
