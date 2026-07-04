package ecom.services;

import ecom.discount.IDiscountStrategy;
import ecom.entities.order.Order;

public interface IPricingService {
    float calculateTotal(Order order, IDiscountStrategy discountStrategy);
}
