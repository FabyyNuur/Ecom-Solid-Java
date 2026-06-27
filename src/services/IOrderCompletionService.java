package services;

import entities.order.Order;

public interface IOrderCompletionService {
    void complete(Order order, float total);
}
