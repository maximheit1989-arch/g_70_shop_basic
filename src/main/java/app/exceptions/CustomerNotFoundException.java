package app.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(String.format("Покупатель с идентификатором %d не найден", id));
    }
}
