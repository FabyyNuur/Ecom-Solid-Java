package services;

import discount.IDiscountStrategy;
import entities.order.Order;
import entities.order.OrderLine;

public class PricingService implements IPricingService {
    @Override
    public float calculateTotal(Order order, IDiscountStrategy discountStrategy) {
        float discountedSubtotal = discountStrategy.applyDiscount(order.getSubtotal());
        return discountedSubtotal + calculateShipping(order);
    }

    private float calculateShipping(Order order) {
        float shipping = 0;
        for (OrderLine line : order.getLines()) {
            shipping += line.getProduct().getShippingCost(line.getQuantity());
        }
        return shipping;
    }
}
