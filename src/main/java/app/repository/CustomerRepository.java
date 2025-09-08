package app.repository;

import app.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {

    private final Map<Long, Customer> database = new HashMap<>();
    private long maxId;

    public Customer save(Customer customer) {
        customer.setId(++maxId);
        database.put(maxId, customer);
        return customer;
    }

    public List<Customer> findAll() {
        // Как это работает:
        // 1. Метод values() возвращает нам коллекцию значений мапа (коллекцию покупателей)
        // 2. Эту коллекцию покупателей мы передаём в конструктор ArrayList.
        // 3. Создаётся ArrayList из тех же покупателей, которых вернул нам метод values().
        return new ArrayList<>(database.values());
    }

    // Задача этого метода найти покупателя по его ID.
    public Customer findById(Long id) {
        return database.get(id);
    }

    // Задача этого метода изменить у покупателя его имя
    public void update(Long id, String newName) {
        Customer customer = findById(id);
        if (customer != null) {
            customer.setName(newName);
        }
    }

    // Удаление по идентификатору
    public void deleteById(Long id) {
        database.remove(id);
    }
}
