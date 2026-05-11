package patterns;

import enums.ManagerType;
import enums.TeacherTitle;
import models.Admin;
import models.BachelorStudent;
import models.GraduateStudent;
import models.Manager;
import models.Teacher;
import models.User;

/**
 * Factory pattern -- one entry point creates whichever User subtype
 * the caller asks for. Hides constructor differences from the caller.
 */
public class UserFactory {

    public static User createUser(String type, String login, String password) {
        if (type == null) throw new IllegalArgumentException("type must not be null");
        switch (type.toLowerCase()) {
            case "bachelor":
                return new BachelorStudent(login, password, "SITE", "CS", 1);
            case "master":
                return new GraduateStudent(login, password, "SITE", "CS", 1, true);
            case "phd":
                return new GraduateStudent(login, password, "SITE", "CS", 1, false);
            case "teacher":
                return new Teacher(login, password, 500000,
                                   "CS", TeacherTitle.LECTURER);
            case "professor":
                return new Teacher(login, password, 800000,
                                   "CS", TeacherTitle.PROFESSOR);
            case "admin":
                return new Admin(login, password, 400000, "IT", 5);
            case "manager":
                return new Manager(login, password, 600000, "OR",
                                   ManagerType.OR, "SITE");
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
}
