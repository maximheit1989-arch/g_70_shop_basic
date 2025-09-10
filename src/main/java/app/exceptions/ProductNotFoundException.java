package app.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super(String.format("Продукт с идентификтором %d не найден", id));
    }
}
