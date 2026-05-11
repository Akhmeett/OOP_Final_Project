package models;

import enums.TeacherTitle;
import academic.Course;
import academic.Mark;
import exceptions.NotResearcherException;
import research.Researcher;
import research.ResearchPaper;
import research.ResearchProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Teacher is an Employee that may also be a Researcher.
 * Only PROFESSOR / ASSOCIATE_PROFESSOR are flagged as researchers by default,
 * but the flag can be changed via setResearcher(...).
 */
public class Teacher extends Employee implements Researcher {
    private static final long serialVersionUID = 1L;

    private TeacherTitle title;
    private List<Course> courses;
    private double rating;
    private boolean researcher;

    private List<ResearchPaper> papers;
    private List<ResearchProject> projects;

    public Teacher(String login, String password,
                   double salary, String department,
                   TeacherTitle title) {
        super(login, password, salary, department);
        this.title = title;
        this.courses = new ArrayList<>();
        this.rating = 0.0;
        this.papers = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.researcher = (title == TeacherTitle.PROFESSOR
                        || title == TeacherTitle.ASSOCIATE_PROFESSOR);
    }

    public void setResearcher(boolean researcher) { this.researcher = researcher; }
    public boolean isResearcher() { return researcher; }

    // ----- Researcher interface -----

    @Override
    public void addPaper(ResearchPaper p) {
        if (!researcher) {
            throw new NotResearcherException(
                getLogin() + " is not a Researcher!");
        }
        this.papers.add(p);
        System.out.println("Paper added by " + getLogin() + ": " + p.getTitle());
    }

    @Override
    public void joinProject(ResearchProject p) {
        if (!researcher) {
            throw new NotResearcherException(
                getLogin() + " is not a Researcher!");
        }
        p.addParticipant(this);
        this.projects.add(p);
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        papers.stream().sorted(c).forEach(System.out::println);
    }

    /**
     * Standard h-index: largest h such that at least h papers
     * have >= h citations each.
     */
    @Override
    public int calculateHIndex() {
        if (!researcher || papers.isEmpty()) return 0;
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

    // ----- Teacher-only behaviour -----

    public void putMark(Student s, Course c, Mark mark) {
        s.getTranscript().add(mark);
        System.out.println("Mark added for " + s.getLogin());
    }

    public void sendComplaint(Student s, String msg) {
        System.out.println("Complaint about " + s.getLogin() + ": " + msg);
    }

    public List<Student> viewStudents() {
        List<Student> all = new ArrayList<>();
        for (Course c : courses) all.addAll(c.getStudents());
        return all;
    }

    public TeacherTitle getTitle() { return title; }
    public List<Course> getCourses() { return courses; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public String getInfo() {
        return "Teacher: " + getLogin()
             + ", Title: " + title
             + ", Researcher: " + researcher
             + ", H-index: " + calculateHIndex();
    }
}
