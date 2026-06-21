package services;

import discount.IDiscountStrategy;
import entities.order.Order;

public class OrderManager {
    private final StockService stockService;
    private final PricingService pricingService;
    private final OrderCompletionService orderCompletionService;

    public OrderManager(StockService stockService,
                        PricingService pricingService,
                        OrderCompletionService orderCompletionService) {
        this.stockService = stockService;
        this.pricingService = pricingService;
        this.orderCompletionService = orderCompletionService;
    }

    public void processOrder(Order order, IDiscountStrategy discountStrategy) {
        stockService.fulfill(order);
        float total = pricingService.calculateTotal(order, discountStrategy);
        orderCompletionService.complete(order, total);
    }
}
