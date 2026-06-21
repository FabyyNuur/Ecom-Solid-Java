package entities;

public abstract class Product implements IProduct {
    protected String name;
    protected float price;

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() { return name; }

    @Override
    public float getPrice() { return price; }
}
