package services;

import discount.IDiscountStrategy;
import entities.IShippable;
import entities.order.Order;
import entities.order.OrderLine;
import entities.IProduct;

public class PricingService {
    public float calculateTotal(Order order, IDiscountStrategy discountStrategy) {
        float discountedSubtotal = discountStrategy.applyDiscount(order.getSubtotal());
        return discountedSubtotal + calculateShipping(order);
    }

    private float calculateShipping(Order order) {
        float shipping = 0;
        for (OrderLine line : order.getLines()) {
            IProduct product = line.getProduct();
            if (product instanceof IShippable shippable) {
                shipping += shippable.calculateShippingCost() * line.getQuantity();
            }
        }
        return shipping;
    }
}
