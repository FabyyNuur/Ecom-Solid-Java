package ecom.services;

import ecom.entities.order.Order;
import ecom.repositories.IOrderRepository;
import ecom.services.notification.IEmailSender;

public class OrderCompletionService implements IOrderCompletionService {
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

    @Override
    public void complete(Order order, float total) {
        order.setTotal(total);
        String userEmail = order.getClient().getEmail();
        invoiceService.generatePdfInvoice(userEmail, total);
        emailSender.sendEmail(userEmail, "Votre commande de " + total + "€ est confirmée. Merci pour votre achat ! \n");
        orderRepository.save(order);
    }
}
