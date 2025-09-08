package app.repository;

import app.domain.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Этот класс находится на 2 слое нашего приложения - репозитории.
// Задача репозитория - осуществлять доступ к данным, которые хранятся в базе данных.
// В качестве имитации базы данных у нас здесь будет выступать Лист.

// Репозиторий, как правило, реализует CRUD - функционал.
// CRUD - Create, Read, Update, Delete.
public class ProductRepository {

    // Имитация базы данных
    private final List<Product> database = new ArrayList<>();

    // Поле, которое учитывает, какой сейчас максимальный ID продукта в базе данных.
    private long maxId;

    // Метод, который сохраняет новый продукт в базе данных (Create)
    public Product save(Product product){
        product.setId(++maxId);
        database.add(product);
        return product;
    }

    // Метод, который возвращает все продукты из базы данных (Read)
    public List<Product> findAll(){
        return database;
    }

    // Метод, который возвращает один конкретный продукт по идентификатору
    public Product findById(Long id){
        for (Product product : database) {
            if(product.getId().equals(id)){
                return product;
            }
        } return null;
    }

    // Метод, который изменяет цену продукта в базе данных (Update)
    public void update(Long id, double newPrice){
        for (Product product : database) {
            if(product.getId().equals(id)){
                product.setPrice(newPrice);
                break;
            }
        }
    }

    // Метод, который удаляет продукт из базы данных (Delete)
    public void deleteById(Long id){
        Iterator<Product> iterator = database.iterator();
        while (iterator.hasNext()){
            if (iterator.next().getId().equals(id)){
                iterator.remove();
                break;
            }
        }
    }
}
