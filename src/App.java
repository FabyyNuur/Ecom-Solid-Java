import java.util.Scanner;

import entity.Product;
import services.InventoryManager;
import services.InvoiceGenerator;
import services.NotificationService;
import services.OrderManager;
import services.discount.BlackFridayDiscountStrategy;
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
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Produit : " + product.name + " | Prix : " + product.price + "€ | Stock : " + product.stock);
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1- Passer une commande");
            System.out.println("2- Quitter");

            int choix = scanner.nextInt();

            if (choix == 1) {
                try {
                    System.out.println("Entrez la quantite");
                    int quantity = scanner.nextInt();
                    System.out.println("Type de reduction (VIP/BLACK_FRIDAY/STUDENT)");
                    String discountType = scanner.next();
                    System.out.println("Entrez l'email du client");
                    String userEmail = scanner.next();

                    IDiscountStrategy strategy;
                    if (discountType.equals("VIP")) {
                        strategy = new VipDiscountStrategy();
                    } else if (discountType.equals("BLACK_FRIDAY")) {
                        strategy = new BlackFridayDiscountStrategy();
                    } else if (discountType.equals("STUDENT")) {
                        strategy = new StudentDiscountStrategy();
                    } else {
                        strategy = new VipDiscountStrategy();
                    }

                    orderManager.processOrder(product, quantity, strategy, userEmail);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (choix == 2) {
                running = false;
            }
        }

        scanner.close();
    }
}
