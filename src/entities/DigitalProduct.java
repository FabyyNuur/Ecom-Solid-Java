package entities;


public class DigitalProduct implements IDigitalProduct {
    private final String name;
    private final float price;

    public DigitalProduct(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() { return name; }

    @Override
    public float getPrice() { return price; }

    @Override
    public String toString() {
        return "Produit : " + name + " | Prix : " + price + "€ | Stock : ∞";
    }
}
