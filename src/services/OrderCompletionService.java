package services;

import entities.Order;
import repositories.IOrderRepository;

public class OrderCompletionService {
    private final InvoiceGenerator invoiceGenerator;
    private final NotificationService notificationService;
    private final IOrderRepository orderRepository;

    public OrderCompletionService(InvoiceGenerator invoiceGenerator,
                                  NotificationService notificationService,
                                  IOrderRepository orderRepository) {
        this.invoiceGenerator = invoiceGenerator;
        this.notificationService = notificationService;
        this.orderRepository = orderRepository;
    }

    public void complete(Order order, float total) {
        String userEmail = order.getClient().getEmail();
        invoiceGenerator.generatePdfInvoice(userEmail, total);
        notificationService.sendEmail(userEmail, "Votre commande de " + total + "€ est confirmée. Merci pour votre achat ! \n");
        orderRepository.save(order);
    }
}
