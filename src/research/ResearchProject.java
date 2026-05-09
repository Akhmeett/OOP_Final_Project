package research;

import exceptions.NotResearcherException;
import java.util.ArrayList;
import java.util.List;

public class ResearchProject {
    private String topic;
    private List<Researcher> participants;
    private List<ResearchPaper> papers;

    public ResearchProject(String topic) {
        this.topic = topic;
        this.participants = new ArrayList<>();
        this.papers = new ArrayList<>();
    }

    public void addParticipant(Object r) {
        if (!(r instanceof Researcher))
            throw new NotResearcherException(r + " is not a Researcher!");
        participants.add((Researcher) r);
        System.out.println("Joined project: " + topic);
    }

    public String getTopic() { return topic; }
    public List<Researcher> getParticipants() { return participants; }
    public List<ResearchPaper> getPapers() { return papers; }

    @Override
    public String toString() {
        return "ResearchProject: " + topic +
               ", participants: " + participants.size();
    }
}