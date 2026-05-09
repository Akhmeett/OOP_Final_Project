package models;

import enums.TeacherTitle;
import academic.Course;
import academic.Mark;
import interfaces.Researcher;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends Employee implements Researcher {
    private TeacherTitle title;
    private List<Course> courses;
    private double rating;
    
    private boolean isResearcher;
    
    private List<Object> papers = new ArrayList<>();
    private List<Object> projects = new ArrayList<>();

    public Teacher(String login, String password, double salary, String department, TeacherTitle title) {
        super(login, password, salary, department);
        this.title = title;
        this.courses = new ArrayList<>();
        this.rating = 0.0;
        
        this.isResearcher = (title == TeacherTitle.PROFESSOR);
    }

    public void setAsResearcher(boolean isResearcher) {
        this.isResearcher = isResearcher;
    }

    public boolean isResearcher() {
        return isResearcher;
    }


    @Override
    public void addPaper(Object p) {
        if (isResearcher) {
            this.papers.add(p);
            System.out.println("Статья успешно добавлена.");
        } else {
            System.out.println("Ошибка: У вас нет прав исследователя.");
        }
    }

    @Override
    public void joinProject(Object p) {
        if (isResearcher) {
            this.projects.add(p);
            System.out.println("Успешно добавлено в проект.");
        } else {
            System.out.println("Ошибка: Только исследователи могут участвовать в проектах.");
        }
    }

    @Override
    public void printPapers(Object c) {
        if (isResearcher) {
            
        }
    }

    @Override
    public int calculateHIndex() {
        if (isResearcher) return 0;
        return 0;
    }

    @Override
    public List<Object> getProjects() {
        return isResearcher ? this.projects : new ArrayList<>();
    }

    @Override
    public List<Object> getPapers() {
        return isResearcher ? this.papers : new ArrayList<>();
    }

  

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

    @Override
    public String getInfo() {
        return "Teacher: " + getLogin() + ", Title: " + title + ", Is Researcher: " + isResearcher;
    }
}