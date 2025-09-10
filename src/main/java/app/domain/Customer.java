package app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {

    private Long id;
    private String name;
    private boolean active;
    private List<Product> cart = new ArrayList<>();

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Product> getCart() {
        return cart;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && active == customer.active && Objects.equals(name, customer.name) && Objects.equals(cart, customer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active, cart);
    }

    @Override
    public String toString() {
        String info = String.format("Customer: id - %d, name - %s, active - %s%n", id, name, active ? "yes" : "no");
        StringBuilder builder = new StringBuilder(info);
        builder.append("Cart:");
        cart.forEach(x -> builder.append(" ").append(x.getTitle()));
        return builder.toString();

    }
}
