package repositories.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.Order;
import repositories.IOrderRepository;

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
