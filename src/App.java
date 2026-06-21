import java.util.List;
import java.util.Scanner;

import discount.BlackFridayDiscountStrategy;
import discount.IDiscountStrategy;
import discount.StudentDiscountStrategy;
import discount.VipDiscountStrategy;
import entities.Client;
import entities.DigitalProduct;
import entities.IProduct;
import entities.Order;
import entities.IPhysicalProduct;
import entities.PhysicalProduct;
import repositories.IOrderRepository;
import repositories.impl.OrderRepositoryImpl;
import services.IEmailSender;
import services.IInvoiceService;
import services.InvoiceGenerator;
import services.EmailNotificationService;
import services.OrderCompletionService;
import services.OrderManager;
import services.PricingService;
import services.StockService;

public class App {
    public static void main(String[] args) {
        IOrderRepository orderRepository = new OrderRepositoryImpl();
        PricingService pricingService = new PricingService();
        IInvoiceService invoiceService = new InvoiceGenerator();
        IEmailSender emailSender = new EmailNotificationService();
        OrderCompletionService orderCompletionService = new OrderCompletionService(
                invoiceService, emailSender, orderRepository);
        StockService stockService = new StockService();
        OrderManager orderManager = new OrderManager(stockService, pricingService, orderCompletionService);

        IProduct[] catalog = {
            new PhysicalProduct("Laptop", 999.99f, 10),
            new DigitalProduct("Guide PDF SOLID", 19.99f)
        };
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
            System.out.println("Catalogue :");
            for (int i = 0; i < catalog.length; i++) {
                System.out.println((i + 1) + "- " + catalog[i]);
            }
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1- Passer une commande");
            System.out.println("2- Afficher les commandes enregistrees");
            System.out.println("3- Quitter");

            if (!scanner.hasNextInt()) {
                System.out.println("Entree invalide. Veuillez saisir un nombre.");
                scanner.nextLine();
                continue;
            }

            int choix = scanner.nextInt();
            scanner.nextLine();

            if (choix == 1) {
                try {
                    System.out.print("Choisissez un produit (1-" + catalog.length + ") : ");
                    int productChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (productChoice < 1 || productChoice > catalog.length) {
                        throw new IllegalArgumentException("Choix de produit invalide : " + productChoice);
                    }
                    IProduct product = catalog[productChoice - 1];

                    System.out.print("Entrez la quantite : ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    if (product instanceof IPhysicalProduct physical) {
                        physical.checkStock(quantity);
                    }

                    System.out.println("Type de reduction :");
                    for (int j = 0; j < discountLabels.length; j++) {
                        System.out.println((j + 1) + "- " + discountLabels[j]);
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
                    String message = e.getMessage();
                    System.out.println("Erreur : " + (message != null ? message : e.getClass().getSimpleName()));
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
