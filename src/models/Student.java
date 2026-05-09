package models;

import academic.Course;
import academic.Mark;
import exceptions.CreditLimitException;
import exceptions.FailLimitException;
import exceptions.LowHIndexException;
import research.Researcher;
import java.util.ArrayList;
import java.util.List;

public abstract class Student extends User {
    private double gpa;
    private int credits;
    private int failCount;
    private List<Mark> transcript;
    private int year;
    private String school;
    private String department;
    private final int MAX_CREDITS = 21;
    private final int studyYears = 4;
    private Researcher supervisor;

    public Student(String login, String password,
                   String school, String department, int year) {
        super(login, password);
        this.school = school;
        this.department = department;
        this.year = year;
        this.transcript = new ArrayList<>();
        this.gpa = 0.0;
        this.credits = 0;
        this.failCount = 0;
    }

    public void registerCourse(Course c) {
        if (credits + c.getCredits() > maxCredits)
            throw new CreditLimitException("Max credits exceeded! Max=" + maxCredits);
        if (failCount >= 3)
            throw new FailLimitException("Failed too many times!");
        c.getStudents().add(this);
        credits += c.getCredits();
        System.out.println(getLogin() + " registered for " + c.getName());
    }

    public void rateTeacher(Teacher t, double score) {
        t.setRating((t.getRating() + score) / 2);
    }

    public void assignSupervisor(Researcher r) {
        if (r.calculateHIndex() < 3)
            throw new LowHIndexException("H-index must be >= 3!");
        this.supervisor = r;
    }

    public List<Mark> getTranscript() { return transcript; }
    public double getGpa() { return gpa; }
    public int getCredits() { return credits; }
    public int getFailCount() { return failCount; }
    public int getYear() { return year; }
    public String getSchool() { return school; }
    public int getMaxCredits() { return maxCredits; }
    public Researcher getSupervisor() { return supervisor; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public void incrementFail() { this.failCount++; }

    @Override
    public String getInfo() {
        return "Student: " + getLogin() + ", GPA: " + gpa + ", Credits: " + credits;
    }
}