import academic.Course;
import academic.Mark;
import communication.Message;
import database.DataStorage;
import enums.ManagerType;
import enums.TeacherTitle;
import exceptions.CreditLimitException;
import exceptions.LowHIndexException;
import exceptions.NotResearcherException;
import models.Admin;
import models.BachelorStudent;
import models.Employee;
import models.GraduateStudent;
import models.Manager;
import models.Student;
import models.Teacher;
import models.User;
import patterns.MarkReportStrategy;
import patterns.NewsPublisher;
import patterns.Observer;
import patterns.ReportGenerator;
import patterns.ResearchReportStrategy;
import patterns.UserFactory;
import research.CitationComparator;
import research.DateComparator;
import research.PageComparator;
import research.ResearchPaper;
import research.ResearchProject;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Demo entry point. Walks through every responsibility of the project:
 *   - models (User hierarchy, abstraction, inheritance, polymorphism)
 *   - academic (Course, Mark)
 *   - research (Researcher interface, papers, h-index, comparators)
 *   - communication (Message, overloaded sendMessage)
 *   - patterns (Singleton, Factory, Observer, Strategy)
 *   - exceptions (CreditLimit, LowHIndex, NotResearcher)
 */
public class Main {

    public static void main(String[] args) {
        line("1. FACTORY: create users without exposing constructors");
        User u1 = UserFactory.createUser("bachelor",  "alice",  "pw");
        User u2 = UserFactory.createUser("master",    "bob",    "pw");
        User u3 = UserFactory.createUser("professor", "prof_x", "pw");
        User u4 = UserFactory.createUser("manager",   "mgr_y",  "pw");
        User u5 = UserFactory.createUser("admin",     "root",   "pw");

        // Polymorphism: same call, different implementations.
        List<User> users = Arrays.asList(u1, u2, u3, u4, u5);
        for (User u : users) {
            System.out.println(" - " + u.getInfo());
        }

        line("2. ACADEMIC: Course + credit limit (custom exception)");
        BachelorStudent alice = (BachelorStudent) u1;
        Course oop  = new Course("CS201", "Object-Oriented Programming", 6);
        Course math = new Course("MA101", "Calculus I",                  8);
        Course phys = new Course("PH101", "Physics I",                   8);

        alice.registerCourse(oop);
        alice.registerCourse(math);
        try {
            // alice already has 14 credits; phys (8) would push to 22 > 21
            alice.registerCourse(phys);
        } catch (CreditLimitException e) {
            System.out.println("Caught CreditLimitException: " + e.getMessage());
        }

        line("3. TEACHER: putMark + overloaded sendMessage");
        Teacher prof = (Teacher) u3;
        Manager mgr  = (Manager) u4;
        prof.putMark(alice, oop, new Mark(80, 90, 85, oop));
        prof.sendMessage(mgr, "Please approve registrations");
        prof.sendMessage(mgr, "URGENT", "Late registrations need approval");

        line("4. RESEARCH: papers, h-index, NotResearcher exception");
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 10);
        Date d2024 = cal.getTime();
        cal.set(2022, Calendar.JUNE, 5);
        Date d2022 = cal.getTime();
        cal.set(2020, Calendar.MARCH, 1);
        Date d2020 = cal.getTime();

        ResearchPaper p1 = new ResearchPaper("Neural Nets in 2020",
                Arrays.asList("prof_x"), "AI Journal", "10.1/aj.1", 12, d2020);
        p1.setCitations(15);
        ResearchPaper p2 = new ResearchPaper("Graphs at Scale",
                Arrays.asList("prof_x", "bob"), "Algo Journal", "10.1/aj.2", 30, d2022);
        p2.setCitations(8);
        ResearchPaper p3 = new ResearchPaper("Transformers Revisited",
                Arrays.asList("prof_x"), "AI Journal", "10.1/aj.3", 22, d2024);
        p3.setCitations(3);

        prof.addPaper(p1);
        prof.addPaper(p2);
        prof.addPaper(p3);
        System.out.println("prof_x h-index = " + prof.calculateHIndex());

        // Citation formats: overloaded getCitation()
        System.out.println("Default citation: " + p1.getCitation());
        System.out.println("Bibtex citation:  " + p1.getCitation("Bibtex"));

        // Trying research with a non-researcher Teacher
        Teacher tutor = new Teacher("t_tutor", "pw", 300000, "CS", TeacherTitle.TUTOR);
        try {
            tutor.addPaper(p1);
        } catch (NotResearcherException e) {
            System.out.println("Caught NotResearcherException: " + e.getMessage());
        }

        line("5. SUPERVISOR: assign supervisor (h-index check)");
        GraduateStudent bob = (GraduateStudent) u2;
        try {
            bob.assignSupervisor(prof);
            System.out.println(bob.getLogin() + " got supervisor "
                    + prof.getLogin() + " (h=" + prof.calculateHIndex() + ")");
        } catch (LowHIndexException e) {
            System.out.println("Caught LowHIndexException: " + e.getMessage());
        }

        line("6. COMPARATORS: same papers, three orderings");
        List<ResearchPaper> papers = prof.getPapers();
        System.out.println("Natural order (Comparable, by citations desc):");
        List<ResearchPaper> copy = new java.util.ArrayList<>(papers);
        Collections.sort(copy);
        copy.forEach(p -> System.out.println(" - " + p));

        System.out.println("CitationComparator:");
        prof.printPapers(new CitationComparator());
        System.out.println("DateComparator (newest first):");
        prof.printPapers(new DateComparator());
        System.out.println("PageComparator (shortest first):");
        prof.printPapers(new PageComparator());

        line("7. PROJECT: only Researchers can join");
        ResearchProject proj = new ResearchProject("Quantum AI");
        prof.joinProject(proj);
        bob.joinProject(proj);
        System.out.println(proj);

        line("8. STRATEGY: swap report algorithm at runtime");
        ReportGenerator gen = new ReportGenerator(new MarkReportStrategy(alice));
        System.out.println(gen.generate());
        gen.setStrategy(new ResearchReportStrategy(prof, "prof_x"));
        System.out.println(gen.generate());

        line("9. OBSERVER: NewsPublisher notifies subscribers");
        NewsPublisher news = new NewsPublisher();
        // Concrete observers via anonymous classes (also a tiny lambda demo).
        Observer aliceObs = msg ->
            System.out.println("alice received: " + msg);
        Observer bobObs = msg ->
            System.out.println("bob received: "   + msg);
        news.subscribe(aliceObs);
        news.subscribe(bobObs);
        news.publish("Exams start on December 15");
        news.unsubscribe(bobObs);
        news.publish("Library hours extended");

        line("10. SINGLETON DataStorage: save / load");
        DataStorage db1 = DataStorage.getInstance();
        DataStorage db2 = DataStorage.getInstance();
        System.out.println("Same instance? " + (db1 == db2));
        db1.save("alice",  alice);
        db1.save("prof_x", prof);
        Object back = db2.load("alice");
        System.out.println("Reloaded: " + back);

        line("11. ADMIN demo + manager overloaded createReport");
        Admin admin = (Admin) u5;
        admin.manageUsers();
        admin.viewLogs();
        System.out.println(mgr.createReport());
        System.out.println(mgr.createReport("monthly"));

        line("DONE");
    }

    private static void line(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
