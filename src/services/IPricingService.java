package services;

import discount.IDiscountStrategy;
import entities.order.Order;

public interface IPricingService {
    float calculateTotal(Order order, IDiscountStrategy discountStrategy);
}
