package communication;

import models.Employee;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private Employee from;
    private Employee to;
    private String body;
    private Date date;

    public Message(Employee from, Employee to, String body) {
        this.from = from;
        this.to = to;
        this.body = body;
        this.date = new Date();
    }

    public void send() {
        System.out.println("[" + date + "] "
            + from.getLogin() + " -> "
            + to.getLogin() + ": " + body);
    }

    public Employee getFrom() { return from; }
    public Employee getTo() { return to; }
    public String getBody() { return body; }
    public Date getDate() { return date; }

    @Override
    public String toString() {
        return "Message{from=" + from.getLogin()
             + ", to=" + to.getLogin()
             + ", body='" + body + "'}";
    }
}
