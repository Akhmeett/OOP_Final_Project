package exceptions;

public class FailLimitException extends RuntimeException {
    public FailLimitException(String message) {
        super(message);
    }
}