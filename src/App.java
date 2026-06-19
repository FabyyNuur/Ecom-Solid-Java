import entity.Product;
import services.InventoryManager;
import services.InvoiceGenerator;
import services.NotificationService;
import services.OrderManager;
import services.discount.IDiscountStrategy;
import services.discount.StudentDiscountStrategy;
import services.discount.VipDiscountStrategy;

public class App {
    public static void main(String[] args) throws Exception {
        InventoryManager inventoryManager = new InventoryManager();
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        NotificationService notificationService = new NotificationService();
        OrderManager orderManager = new OrderManager(inventoryManager, invoiceGenerator, notificationService);

        Product product = new Product("Laptop", 999.99f, 10);

        IDiscountStrategy strategy = new VipDiscountStrategy();
        orderManager.processOrder(product, 2, strategy, "client@example.com");

        IDiscountStrategy studentStrategy = new StudentDiscountStrategy();
        orderManager.processOrder(product, 1, studentStrategy, "etudiant@example.com");
    }
}
