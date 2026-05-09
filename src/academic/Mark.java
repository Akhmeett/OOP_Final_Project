package academic;

public class Mark {
    private double att1;
    private double att2;
    private double finalExam;
    private Course course;

    public Mark(double att1, double att2, double finalExam, Course course) {
        this.att1 = att1;
        this.att2 = att2;
        this.finalExam = finalExam;
        this.course = course;
    }

    public double getTotal() {
        return att1 * 0.3 + att2 * 0.3 + finalExam * 0.4;
    }

    public String getGrade() {
        double total = getTotal();
        if (total >= 90) return "A";
        else if (total >= 80) return "B";
        else if (total >= 70) return "C";
        else if (total >= 60) return "D";
        else return "F";
    }

    public boolean isPassed() {
        return getTotal() >= 50;
    }

    public double getAtt1() { return att1; }
    public double getAtt2() { return att2; }
    public double getFinalExam() { return finalExam; }
    public Course getCourse() { return course; }

    @Override
    public String toString() {
        return "Mark{course=" + course.getName() +
               ", total=" + getTotal() +
               ", grade=" + getGrade() + "}";
    }
}