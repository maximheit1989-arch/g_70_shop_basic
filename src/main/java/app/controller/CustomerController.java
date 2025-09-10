package app.controller;

import app.domain.Customer;
import app.service.CustomerService;

import java.util.List;

public class CustomerController {

    private final CustomerService service = new CustomerService();

    // Сохранить покупателя в базе данных (при сохранении покупатель автоматически считается активным).
    public Customer safe(String name) {
        Customer customer = new Customer(name);
        return service.save(customer);
    }

    // Вернуть всех покупателей из базы данных (активных).
    public List<Customer> getAll() {
        return service.getAllActiveCustomers();
    }

    // Вернуть одного покупателя из базы данных по его идентификатору (если он активен).
    public Customer getById(Long id) {
        return service.getActiveCustomerById(id);
    }

    // Изменить одного покупателя в базе данных по его идентификатору.
    public void update(Long id, String newName) {
        service.update(id, newName);
    }

    // Удалить покупателя из базы данных по его идентификатору.
    public void deleteById(Long id) {
        service.deleteById(id);
    }

    // Удалить покупателя из базы данных по его имени.
    public void deleteByName(String name) {
        service.deleteByName(name);
    }

    // Восстановить удалённого покупателя в базе данных по его идентификатору.
    public void restoreById(Long id) {
        service.restoreById(id);
    }

    // Вернуть общее количество покупателей в базе данных (активных).
    public int getCustomersNumber() {
        return service.getActiveCustomersNumber();
    }

    // Вернуть стоимость корзины покупателя по его идентификатору (если он активен).
    public double getCustomersCartTotalCost(Long id) {
        return service.getCustomersCartTotalCost(id);
    }

    // Вернуть среднюю стоимость продукта в корзине покупателя по его идентификатору (если он активен)
    public double getCustomersCartAveragePrice(Long id) {
        return service.getCustomersCartAveragePrice(id);
    }

    // Добавить товар в корзину покупателя по их идентификаторам (если оба активны)
    public void addProductToCustomersCart(Long customerId, Long productId) {
        service.addProductCustomersCart(customerId, productId);
    }

    // Удалить товар из корзины покупателя по их идентификаторам
    public void removeProductFromCustomersCart(Long customerId, Long productId) {
        service.removeProductFromCustomersCart(customerId, productId);
    }

    // Полностью очистить корзину покупателя по его идентификатору (если он активен)
    public void clearCustomersCart(Long customerId) {
        service.clearCustomersCart(customerId);
    }
}
