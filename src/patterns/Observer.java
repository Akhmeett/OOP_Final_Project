package patterns;

/**
 * Observer pattern -- subscriber interface.
 * Any class can become an observer by implementing update(...).
 */
public interface Observer {
    void update(String news);
}
