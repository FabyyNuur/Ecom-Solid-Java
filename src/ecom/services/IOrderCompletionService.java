package ecom.services;

import ecom.entities.order.Order;

public interface IOrderCompletionService {
    void complete(Order order, float total);
}
