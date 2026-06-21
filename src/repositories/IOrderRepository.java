package repositories;

import java.util.List;

import entities.Order;

public interface IOrderRepository {
    void save(Order order);

    List<Order> findAll();
}
