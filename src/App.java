import java.util.List;
import java.util.Scanner;

import discount.BlackFridayDiscountStrategy;
import discount.IDiscountStrategy;
import discount.StudentDiscountStrategy;
import discount.VipDiscountStrategy;
import entities.Client;
import entities.Order;
import entities.Product;
import repositories.IOrderRepository;
import repositories.impl.OrderRepositoryImpl;
import services.InvoiceGenerator;
import services.NotificationService;
import services.OrderCompletionService;
import services.OrderManager;
import services.PricingService;

public class App {
    public static void main(String[] args) {
        IOrderRepository orderRepository = new OrderRepositoryImpl();
        PricingService pricingService = new PricingService();
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        NotificationService notificationService = new NotificationService();
        OrderCompletionService orderCompletionService = new OrderCompletionService(
                invoiceGenerator, notificationService, orderRepository);
        OrderManager orderManager = new OrderManager(pricingService, orderCompletionService);

        Product product = new Product("Laptop", 999.99f, 10);
        Scanner scanner = new Scanner(System.in);

        IDiscountStrategy[] discountStrategies = {
            new VipDiscountStrategy(),
            new BlackFridayDiscountStrategy(),
            new StudentDiscountStrategy()
        };
        String[] discountLabels = {"VIP", "BLACK_FRIDAY", "STUDENT"};

        System.out.print("Entrez l'email du client : ");
        Client client = new Client(scanner.nextLine().trim());
        System.out.println(client);

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println(product);
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1- Passer une commande");
            System.out.println("2- Afficher les commandes enregistrees");
            System.out.println("3- Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine();

            if (choix == 1) {
                try {
                    System.out.print("Entrez la quantite : ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    product.checkStock(quantity);

                    System.out.println("Type de reduction :");
                    for (int i = 0; i < discountLabels.length; i++) {
                        System.out.println((i + 1) + "- " + discountLabels[i]);
                    }
                    int discountChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (discountChoice < 1 || discountChoice > discountStrategies.length) {
                        throw new IllegalArgumentException("Choix de reduction invalide : " + discountChoice);
                    }
                    IDiscountStrategy strategy = discountStrategies[discountChoice - 1];

                    Order order = new Order(client);
                    order.addLine(product, quantity);
                    client.addOrder(order);
                    orderManager.processOrder(order, strategy);

                    System.out.println("Commande traitee avec succes.");
                } catch (Exception e) {
                    System.out.println("Erreur : " + e.getMessage());
                }
            } else if (choix == 2) {
                List<Order> orders = orderRepository.findAll();

                if (orders.isEmpty()) {
                    System.out.println("Aucune commande enregistree.");
                } else {
                    System.out.println("Commandes enregistrees :");
                    for (int i = 0; i < orders.size(); i++) {
                        System.out.println((i + 1) + "- " + orders.get(i));
                    }
                }
            } else if (choix == 3) {
                running = false;
            } else {
                System.out.println("Choix invalide.");
            }
        }

        scanner.close();
    }
}
