package research;

import java.io.Serializable;
import java.util.Comparator;

public class DateComparator implements Comparator<ResearchPaper>, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public int compare(ResearchPaper a, ResearchPaper b) {
        return b.getDate().compareTo(a.getDate());
    }
}
