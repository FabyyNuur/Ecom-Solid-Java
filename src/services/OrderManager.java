package services;

import entity.Product;
import services.discount.IDiscountStrategy;

public class OrderManager {
    private final InventoryManager inventoryManager;
    private final InvoiceGenerator invoiceGenerator;
    private final NotificationService notificationService;

    public OrderManager(InventoryManager inventoryManager,
                        InvoiceGenerator invoiceGenerator,
                        NotificationService notificationService) {
        this.inventoryManager = inventoryManager;
        this.invoiceGenerator = invoiceGenerator;
        this.notificationService = notificationService;
    }

    public void processOrder(Product product, int quantity,
                             IDiscountStrategy discountStrategy, String userEmail) throws Exception {
        inventoryManager.checkStock(product, quantity);

        float subtotal = product.price * quantity;
        float total = discountStrategy.applyDiscount(subtotal);

        inventoryManager.deductStock(product, quantity);
        invoiceGenerator.generatePdfInvoice(userEmail, total);
        notificationService.sendEmail(userEmail, "Votre commande de " + total + "€ est confirmée. Merci pour votre achat ! \n");
    }
}
