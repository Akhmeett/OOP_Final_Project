package academic;

import enums.LessonType;
import models.Teacher;

public class Lesson {
    private LessonType type;
    private Teacher teacher;
    private Course course;
    private String room;

    public Lesson(LessonType type, Teacher teacher,
                  Course course, String room) {
        this.type = type;
        this.teacher = teacher;
        this.course = course;
        this.room = room;
    }

    public LessonType getType() { return type; }
    public Teacher getTeacher() { return teacher; }
    public Course getCourse() { return course; }
    public String getRoom() { return room; }

    @Override
    public String toString() {
        return "Lesson{type=" + type +
               ", course=" + course.getName() +
               ", room=" + room + "}";
    }
}