package app.controller;

import app.domain.Product;
import app.service.ProductService;

import java.util.List;
/*
Этот класс находится на четвёртом слое нашего приложения - контроллер.
Задача контроллера - принять запрос от внешней программы (клиента),
запросить необходимую операцию или данные у сервиса и отправить ответ клиенту.
 */
public class ProductController {

    private final ProductService service = ProductService.getInstance();

    // Сохранить продукт в базе данных (при сохранении продукт автоматически считается активным).
    public Product save(String title, double price) {
        Product product = new Product(title, price);
        return service.save(product);
    }

    // Вернуть все продукты из базы данных (активные).
    public List<Product> getAll() {
        return service.getAllActiveProducts();
    }

    // Вернуть один продукт из базы данных по его идентификатору (если он активен).
    public Product getById(Long id) {
        return service.getActiveProductById(id);
    }

    // Изменить один продукт в базе данных по его идентификатору.
    public void update(Long id, double newPrice) {
        service.update(id, newPrice);
    }

    // Удалить продукт из базы данных по его идентификатору.
    public void deleteById(Long id) {
        service.deleteById(id);
    }

    // Удалить продукт из базы данных по его наименованию.
    public void deleteByTitle(String title) {
        service.deleteByTitle(title);
    }

    // Восстановить удалённый продукт в базе данных по его идентификатору.
    public void restoreById(Long id) {
        service.restoreById(id);
    }

    // Вернуть общее количество продуктов в базе данных (активных).
    public int getProductsNumber() {
        return service.getActiveProductsNumber();
    }

    // Вернуть суммарную стоимость всех продуктов в базе данных (активных).
    public double getProductsTotalCost() {
        return service.getActiveProductsTotalCost();
    }

    // Вернуть среднюю стоимость продукта в базе данных (из активных).
    public double getProductsAveragePrice() {
        return service.getActiveProductsAveragePrice();
    }
}
