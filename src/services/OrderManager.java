package services;

import discount.IDiscountStrategy;
import entities.order.Order;

public class OrderManager {
    private final IStockService stockService;
    private final IPricingService pricingService;
    private final IOrderCompletionService orderCompletionService;

    public OrderManager(IStockService stockService,
                        IPricingService pricingService,
                        IOrderCompletionService orderCompletionService) {
        this.stockService = stockService;
        this.pricingService = pricingService;
        this.orderCompletionService = orderCompletionService;
    }

    public void processOrder(Order order, IDiscountStrategy discountStrategy) {
        stockService.checkAvailability(order);
        float total = pricingService.calculateTotal(order, discountStrategy);
        orderCompletionService.complete(order, total);
        stockService.deductStock(order);
        order.getClient().addOrder(order);
    }
}
