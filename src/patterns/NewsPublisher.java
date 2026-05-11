package patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer pattern -- the "Subject".
 * Observers subscribe, then receive a callback whenever publish() is called.
 */
public class NewsPublisher {
    private List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer o) {
        observers.add(o);
    }

    public void unsubscribe(Observer o) {
        observers.remove(o);
    }

    public void publish(String news) {
        System.out.println("== NewsPublisher: " + news + " ==");
        for (Observer o : observers) {
            o.update(news);
        }
    }

    public int subscriberCount() {
        return observers.size();
    }
}
