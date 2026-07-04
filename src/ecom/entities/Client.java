package ecom.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ecom.entities.order.Order;

public class Client {
    private final String email;
    private final List<Order> orders = new ArrayList<>();

    public Client(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    @Override
    public String toString() {
        return "Client : " + email + " | Commandes : " + orders.size();
    }
}
