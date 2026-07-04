package ecom.entities.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ecom.entities.Client;
import ecom.entities.Product;

public class Order {
    private final Client client;
    private final List<OrderLine> lines = new ArrayList<>();
    private Float total;

    public Order(Client client) {
        this.client = client;
    }

    public void addLine(Product product, int quantity) {
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

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTotal() {
        return total != null ? total : getSubtotal();
    }

    public boolean isFinalized() {
        return total != null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commande | Client : ").append(client.getEmail());
        if (isFinalized()) {
            sb.append(" | Total : ").append(total).append("€");
        } else {
            sb.append(" | Sous-total : ").append(getSubtotal()).append("€");
        }
        for (OrderLine line : lines) {
            sb.append("\n   - ").append(line);
        }
        return sb.toString();
    }
}
