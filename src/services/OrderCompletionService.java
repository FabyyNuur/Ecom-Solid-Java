package services;

import entities.order.Order;
import repositories.IOrderRepository;

public class OrderCompletionService {
    private final IInvoiceService invoiceService;
    private final IEmailSender emailSender;
    private final IOrderRepository orderRepository;

    public OrderCompletionService(IInvoiceService invoiceService,
                                  IEmailSender emailSender,
                                  IOrderRepository orderRepository) {
        this.invoiceService = invoiceService;
        this.emailSender = emailSender;
        this.orderRepository = orderRepository;
    }

    public void complete(Order order, float total) {
        String userEmail = order.getClient().getEmail();
        invoiceService.generatePdfInvoice(userEmail, total);
        emailSender.sendEmail(userEmail, "Votre commande de " + total + "€ est confirmée. Merci pour votre achat ! \n");
        orderRepository.save(order);
    }
}
