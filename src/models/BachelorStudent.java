package models;

public class BachelorStudent extends Student {
    private final int maxCredits = 21;
    private final int studyYears = 4;

    public BachelorStudent(String login, String password,
                           String school, String department, int year) {
        super(login, password, school, department, year);
    }

    @Override
    public int getMaxCredits() { return maxCredits; }

    @Override
    public String getInfo() {
        return "BachelorStudent: " + getLogin() +
               ", GPA: " + getGpa() +
               ", Credits: " + getCredits();
    }
}
