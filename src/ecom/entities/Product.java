package ecom.entities;

public abstract class Product {
    protected String name;
    protected float price;

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void checkStock(int quantity) {
        // Produits sans stock physique : rien à vérifier
    }

    public void deductStock(int quantity) {
        // Produits sans stock physique : rien à déduire
    }

    public float getShippingCost(int quantity) {
        return 0f;
    }

    public String getStockDisplay() {
        return "∞";
    }
}
