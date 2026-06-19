import entity.Product;
import services.OrderManager;

public class App {
    public static void main(String[] args) throws Exception {
        Product product = new Product("Laptop", 999.99f, 10);
        OrderManager orderManager = new OrderManager();

        orderManager.processOrder(product, 2, "VIP", "client@example.com");
    }
}
