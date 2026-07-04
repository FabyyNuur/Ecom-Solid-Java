package ecom;

import java.util.List;
import java.util.Scanner;

import ecom.discount.IDiscountStrategy;
import ecom.entities.Client;
import ecom.entities.DigitalProduct;
import ecom.entities.PhysicalProduct;
import ecom.entities.Product;
import ecom.entities.order.Order;
import ecom.ioc.ApplicationContext;
import ecom.repositories.IOrderRepository;
import ecom.services.OrderManager;

public class App {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ApplicationContext();
        context.demarrer();

        OrderManager orderManager = context.get(OrderManager.class);
        IOrderRepository orderRepository = context.get(IOrderRepository.class);
        List<String> discountLabels = context.getDiscountLabels();

        Product[] catalog = {
            new PhysicalProduct("Laptop", 999.99f, 10),
            new DigitalProduct("Guide PDF SOLID", 19.99f)
        };

        runConsole(context, orderManager, orderRepository, catalog, discountLabels);
    }

    private static void runConsole(ApplicationContext context,
                                   OrderManager orderManager,
                                   IOrderRepository orderRepository,
                                   Product[] catalog,
                                   List<String> discountLabels) {
        Scanner scanner = new Scanner(System.in);

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
                    Product product = catalog[productChoice - 1];

                    System.out.print("Entrez la quantite : ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Type de reduction :");
                    for (int j = 0; j < discountLabels.size(); j++) {
                        System.out.println((j + 1) + "- " + discountLabels.get(j));
                    }
                    int discountChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (discountChoice < 1 || discountChoice > discountLabels.size()) {
                        throw new IllegalArgumentException("Choix de reduction invalide : " + discountChoice);
                    }
                    String discountType = discountLabels.get(discountChoice - 1);
                    IDiscountStrategy strategy = context.getDiscount(discountType);

                    Order order = new Order(client);
                    order.addLine(product, quantity);
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
