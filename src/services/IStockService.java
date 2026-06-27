package services;

import entities.order.Order;

public interface IStockService {
    void checkAvailability(Order order);

    void deductStock(Order order);
}
