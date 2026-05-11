package patterns;

import academic.Mark;
import models.Student;

/**
 * Strategy pattern -- concrete: report a student's academic marks.
 */
public class MarkReportStrategy implements ReportStrategy {
    private Student student;

    public MarkReportStrategy(Student student) {
        this.student = student;
    }

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Mark report for ").append(student.getLogin()).append(" ===\n");
        sb.append("GPA: ").append(student.getGpa()).append("\n");
        sb.append("Credits: ").append(student.getCredits()).append("\n");
        if (student.getTranscript().isEmpty()) {
            sb.append("(no marks yet)\n");
        } else {
            for (Mark m : student.getTranscript()) {
                sb.append(" - ").append(m).append("\n");
            }
        }
        return sb.toString();
    }
}
