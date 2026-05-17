package research;

import java.io.Serializable;
import java.util.Comparator;

public class PageComparator implements Comparator<ResearchPaper>, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public int compare(ResearchPaper a, ResearchPaper b) {
        return Integer.compare(a.getPages(), b.getPages());
    }
}
