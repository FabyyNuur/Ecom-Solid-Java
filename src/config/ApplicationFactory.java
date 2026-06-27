package config;

import repositories.IOrderRepository;
import repositories.impl.OrderRepositoryImpl;
import services.EmailNotificationService;
import services.IEmailSender;
import services.IInvoiceService;
import services.IOrderCompletionService;
import services.IPricingService;
import services.IStockService;
import services.InvoiceGenerator;
import services.OrderCompletionService;
import services.OrderManager;
import services.PricingService;
import services.StockService;

public final class ApplicationFactory {
    private ApplicationFactory() {}

    public static IOrderRepository createOrderRepository() {
        return new OrderRepositoryImpl();
    }

    public static OrderManager createOrderManager(IOrderRepository orderRepository) {
        IPricingService pricingService = new PricingService();
        IInvoiceService invoiceService = new InvoiceGenerator();
        IEmailSender emailSender = new EmailNotificationService();
        IOrderCompletionService orderCompletionService = new OrderCompletionService(
                invoiceService, emailSender, orderRepository);
        IStockService stockService = new StockService();
        return new OrderManager(stockService, pricingService, orderCompletionService);
    }
}
