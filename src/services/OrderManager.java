package services;

import discount.IDiscountStrategy;
import entities.Order;

public class OrderManager {
    private final PricingService pricingService;
    private final OrderCompletionService orderCompletionService;

    public OrderManager(PricingService pricingService,
                        OrderCompletionService orderCompletionService) {
        this.pricingService = pricingService;
        this.orderCompletionService = orderCompletionService;
    }

    public void processOrder(Order order, IDiscountStrategy discountStrategy) throws Exception {
        order.fulfillStock();
        float total = pricingService.calculateTotal(order, discountStrategy);
        orderCompletionService.complete(order, total);
    }
}
