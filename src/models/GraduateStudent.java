package models;

import research.Researcher;
import research.ResearchPaper;
import research.ResearchProject;
import java.util.*;

public class GraduateStudent extends Student implements Researcher {
    private List<ResearchPaper> papers;
    private List<ResearchProject> projects;
    private boolean isMaster;

    public GraduateStudent(String login, String password,
                           String school, String department,
                           int year, boolean isMaster) {
        super(login, password, school, department, year);
        this.isMaster = isMaster;
        this.papers = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        papers.stream().sorted(c).forEach(System.out::println);
    }

    @Override
    public int calculateHIndex() {
        List<Integer> cites = new ArrayList<>();
        for (ResearchPaper p : papers) cites.add(p.getCitations());
        Collections.sort(cites, Collections.reverseOrder());
        int h = 0;
        for (int i = 0; i < cites.size(); i++) {
            if (cites.get(i) >= i + 1) h = i + 1;
            else break;
        }
        return h;
    }

    @Override
    public List<ResearchProject> getProjects() { return projects; }

    @Override
    public List<ResearchPaper> getPapers() { return papers; }

    @Override
    public void addPaper(ResearchPaper p) { papers.add(p); }

    @Override
    public void joinProject(ResearchProject p) {
        p.addParticipant(this);
        projects.add(p);
    }

    public boolean isMaster() { return isMaster; }

    @Override
    public String getInfo() {
        return (isMaster ? "Master" : "PhD") +
               " Student: " + getLogin() +
               ", H-index: " + calculateHIndex();
    }
}