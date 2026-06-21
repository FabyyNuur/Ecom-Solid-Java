package services;

import discount.IDiscountStrategy;
import entities.Order;

public class PricingService {
    public float calculateTotal(Order order, IDiscountStrategy discountStrategy) {
        return discountStrategy.applyDiscount(order.getSubtotal());
    }
}
