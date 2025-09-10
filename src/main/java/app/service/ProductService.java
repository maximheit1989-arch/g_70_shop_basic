package app.service;

import app.domain.Product;
import app.exceptions.ProductNotFoundException;
import app.exceptions.ProductSaveException;
import app.exceptions.ProductUpdateException;
import app.repository.ProductRepository;

import java.util.List;

/*
Этот класс находится на третье слое нашего приложения - слой сервисов.
Сервисы содержат всю бизнес-логику, то есть логику по всей необходимой
обработке объектов для целей нашего приложения.
Сервис не имеет прямого доступа к базе данных. Чтобы работать с данными,
сервис обращается к репозиториям.
 */
public class ProductService {

    private final ProductRepository repository = new ProductRepository();

    //  Функционал сервиса продуктов:

    //  Сохранить продукт в базе данных (при сохранении продукт автоматически считается активным).
    public Product save(Product product) {
        if (product == null) {
            throw new ProductSaveException("Продукт не может быть null!");
        }

        String title = product.getTitle();
        // trim(): "  Banana   " -> "Banana"
        if (title == null || title.trim().isEmpty()) {
            throw new ProductSaveException("Наименование продукта не должно быть пустым!");
        }

        if (product.getPrice() < 0) {
            throw new ProductSaveException("Цена продукта не должна быть отрицательной!");
        }
        product.setActive(true);
        return repository.save(product);
    }

    //  Вернуть все продукты из базы данных (активные).
    public List<Product> getAllActiveProducts() {
        return repository.findAll()
                .stream()
                .filter(product -> product.isActive())
                .toList();
    }

    //  Вернуть один продукт из базы данных по его идентификатору (если он активен).
    public Product getActiveProductById(Long id) {
        Product product = repository.findById(id);
        if (product == null || !product.isActive()) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    //  Изменить один продукт в базе данных по его идентификатору.
    public void update(Long id, Double newPrice) {
        if (newPrice < 0) {
            throw new ProductUpdateException("Цена продукта не должна быть отрицательной!");
        }
        repository.update(id, newPrice);
    }

    //  Удалить продукт из базы данных по его идентификатору.
    public void deleteById(Long id) {
        Product product = getActiveProductById(id);
        product.setActive(false);
    }

    //  Удалить продукт из базы данных по его наименованию.
    public void deleteByTitle(String title) {
        getAllActiveProducts()
                .stream()
                .filter(x -> x.getTitle().equals(title))
                .forEach(x -> x.setActive(false));
    }

    //  Восстановить удалённый продукт в базе данных по его идентификатору.
    public void restoreById(Long id) {
        Product product = repository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        product.setActive(true);
    }

    //  Вернуть общее количество продуктов в базе данных (активных).
    public int getActiveProductsNumber() {
        return getAllActiveProducts().size();
    }

    //  Вернуть суммарную стоимость всех продуктов в базе данных (активных).
    public double getActiveProductsTotalCost() {
        // 1 Способ:
//        double sum = 0.0;
//        for (Product product : getAllActiveProducts()) {
//            sum += product.getPrice();
//        }
//        return sum;
//    }
        // 2 Способ:
        return getAllActiveProducts()
                .stream()
                .mapToDouble(x -> x.getPrice())
                .sum();
    }

    //  Вернуть среднюю стоимость продукта в базе данных (из активных).
    public double getActiveProductsAveragePrice() {
        // 1 Способ: С использованием предыдущих методов
//        int productNumber = getActiveProductsNumber();
//        if (productNumber == 0) {
//            return 0.0;
//        }
//        return getActiveProductsTotalCost() / productNumber;

        // 2 Способ:
        return getAllActiveProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
    }
}
