package models;

import enums.ManagerType;
import academic.Course;
import communication.Message;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    private ManagerType type;
    private String school;
    private List<Message> requests;

    public Manager(String login, String password,
                   double salary, String department,
                   ManagerType type, String school) {
        super(login, password, salary, department);
        this.type = type;
        this.school = school;
        this.requests = new ArrayList<>();
    }

    public void approveRegistration(Student s, Course c) {
        System.out.println("Approved: " + s.getLogin() + " -> " + c.getName());
    }

    public void assignCourseToTeacher(Course c, Teacher t) {
        c.addTeacher(t);
        t.getCourses().add(c);
        System.out.println("Assigned: " + c.getName() + " -> " + t.getLogin());
    }

    public String createReport() {
        return "Report by: " + getLogin();
    }

    public void manageNews() {
        System.out.println("Managing news...");
    }

    public void createCourse(Course course) {
        System.out.println("Course created: " + course.getName());
    }

    public ManagerType getType() { return type; }

    @Override
    public String getInfo() {
        return "Manager: " + getLogin() + ", Type: " + type;
    }
}
