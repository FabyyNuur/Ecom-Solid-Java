package services;

import entity.Product;

public class OrderManager {
    public void processOrder(Product product, int quantity, String discountType, String userEmail) throws Exception {
        if (product.stock < quantity) {
            throw new Exception("Stock insuffisant pour " + product.name);
        }

        float total = product.price * quantity;
        if (discountType.equals("VIP")) {
            total = total * 0.80f;
        } else if (discountType.equals("BLACK_FRIDAY")) {
            total = total * 0.50f;
        }

        product.stock -= quantity;

        generatePdfInvoice(userEmail, total);
        sendEmail(userEmail, "Votre commande de " + total + "€ est confirmée.");
    }

    private void generatePdfInvoice(String email, float total) {
        System.out.println("Génération de la facture PDF pour " + email + " d'un montant de " + total + "€");
    }

    private void sendEmail(String email, String message) {
        System.out.println("Email envoyé à " + email + " : " + message);
    }
}
