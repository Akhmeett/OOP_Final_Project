package models;

/**
 * Bachelor students are NOT researchers by default.
 * The 'researcher' flag explicitly states this, and can be flipped
 * for unusual cases (e.g. a bachelor who joins a lab in their final year).
 */
public class BachelorStudent extends Student {
    private static final long serialVersionUID = 1L;

    private boolean researcher;

    public BachelorStudent(String login, String password,
                           String school, String department, int year) {
        super(login, password, school, department, year);
        this.maxCredits = 21;
        this.studyYears = 4;
        this.researcher = false;
    }

    public boolean isResearcher() { return researcher; }
    public void setResearcher(boolean researcher) { this.researcher = researcher; }

    @Override
    public String getInfo() {
        return "BachelorStudent: " + getLogin() +
               ", GPA: " + getGpa() +
               ", Credits: " + getCredits() +
               ", Researcher: " + researcher;
    }
}
