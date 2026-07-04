package ecom.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ecom.entities.order.Order;

public class OrderRepositoryImpl implements IOrderRepository {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> findAll() {
        return Collections.unmodifiableList(orders);
    }
}
