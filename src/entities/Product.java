package entities;

public class Product {
    private String name;
    private float price;
    private int stock;

    public Product(String name, float price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void checkStock(int quantity) throws Exception {
        if (stock < quantity) {
            throw new Exception("Stock insuffisant pour " + name);
        }
    }

    public void deductStock(int quantity) throws Exception {
        checkStock(quantity);
        this.stock -= quantity;
    }

    @Override
    public String toString() {
        return "Produit : " + name + " | Prix : " + price + "€ | Stock : " + stock;
    }
}
