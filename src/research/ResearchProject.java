package research;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResearchProject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String topic;
    private List<Researcher> participants;
    private List<ResearchPaper> papers;

    public ResearchProject(String topic) {
        this.topic = topic;
        this.participants = new ArrayList<>();
        this.papers = new ArrayList<>();
    }

    /**
     * Typed parameter -- only Researchers can join.
     * No more raw Object + instanceof check.
     */
    public void addParticipant(Researcher r) {
        participants.add(r);
        System.out.println("Joined project: " + topic);
    }

    public void addPaper(ResearchPaper p) { papers.add(p); }

    public String getTopic() { return topic; }
    public List<Researcher> getParticipants() { return participants; }
    public List<ResearchPaper> getPapers() { return papers; }

    @Override
    public String toString() {
        return "ResearchProject: " + topic +
               ", participants: " + participants.size();
    }
}
