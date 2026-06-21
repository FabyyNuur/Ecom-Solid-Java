package entities.order;

import entities.IProduct;

public class OrderLine {
    private final IProduct product;
    private final int quantity;

    public OrderLine(IProduct product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public IProduct getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return quantity + " x " + product.getName() + " (" + getSubtotal() + "€)";
    }
}
