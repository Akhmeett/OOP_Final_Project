package academic;

import models.Student;
import models.Teacher;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String code;
    private String name;
    private List<Teacher> teachers;
    private List<Student> students;
    private List<Lesson> lessons;
    private int credits;

    public Course(String code, String name, int credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public void addTeacher(Teacher t) { teachers.add(t); }
    public List<Student> getStudents() { return students; }
    public List<Teacher> getTeachers() { return teachers; }
    public List<Lesson> getLessons() { return lessons; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public int getCredits() { return credits; }

    @Override
    public String toString() {
        return "Course{code='" + code + "', name='" + name + "', credits=" + credits + "}";
    }
}