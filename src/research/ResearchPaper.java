package research;

import java.util.Date;
import java.util.List;

public class ResearchPaper implements Comparable<ResearchPaper> {
    private String title;
    private List<String> authors;
    private String journal;
    private String doi;
    private int citations;
    private int pages;
    private Date date;
    private String keywords;

    public ResearchPaper(String title, List<String> authors,
                         String journal, String doi,
                         int pages, Date date) {
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.doi = doi;
        this.pages = pages;
        this.date = date;
        this.citations = 0;
    }

    public String getCitation(String format) {
        if (format.equalsIgnoreCase("Bibtex")) {
            return "@article{" + doi + ",\n" +
                   "  title={" + title + "},\n" +
                   "  journal={" + journal + "}\n}";
        }
        return authors + " (" + date + "). " + title + ". " + journal;
    }

    @Override
    public int compareTo(ResearchPaper other) {
        return Integer.compare(other.citations, this.citations);
    }

    public String getTitle() { return title; }
    public int getCitations() { return citations; }
    public void setCitations(int c) { this.citations = c; }
    public int getPages() { return pages; }
    public Date getDate() { return date; }
    public String getJournal() { return journal; }
    public List<String> getAuthors() { return authors; }

    @Override
    public String toString() {
        return title + " | " + journal + " | citations: " + citations;
    }
}