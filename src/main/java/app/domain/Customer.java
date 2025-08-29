package app.domain;

import java.util.List;
import java.util.Objects;

public class Customer {

    private long id;
    private String name;
    private boolean active;
    private List<Product> cart;

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
