package entities.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.Client;
import entities.IProduct;

public class Order {
    private final Client client;
    private final List<OrderLine> lines = new ArrayList<>();

    public Order(Client client) {
        this.client = client;
    }

    public void addLine(IProduct product, int quantity) {
        lines.add(new OrderLine(product, quantity));
    }

    public Client getClient() {
        return client;
    }

    public List<OrderLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public float getSubtotal() {
        float subtotal = 0;
        for (OrderLine line : lines) {
            subtotal += line.getSubtotal();
        }
        return subtotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commande | Client : ").append(client.getEmail())
                .append(" | Sous-total : ").append(getSubtotal()).append("€");
        for (OrderLine line : lines) {
            sb.append("\n   - ").append(line);
        }
        return sb.toString();
    }
}
