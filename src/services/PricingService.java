package services;

import discount.IDiscountStrategy;
import entities.order.Order;
import entities.order.OrderLine;
import entities.IPhysicalProduct;
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
            if (product instanceof IPhysicalProduct physical) {
                shipping += physical.calculateShippingCost() * line.getQuantity();
            }
        }
        return shipping;
    }
}
