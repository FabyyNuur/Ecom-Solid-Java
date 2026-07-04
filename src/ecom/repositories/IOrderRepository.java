package ecom.repositories;

import java.util.List;

import ecom.entities.order.Order;

public interface IOrderRepository {
    void save(Order order);

    List<Order> findAll();
}
