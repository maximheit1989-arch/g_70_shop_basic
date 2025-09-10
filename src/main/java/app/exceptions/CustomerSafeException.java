package app.exceptions;

public class CustomerSafeException extends RuntimeException {
    public CustomerSafeException(String message) {
        super(message);
    }
}
