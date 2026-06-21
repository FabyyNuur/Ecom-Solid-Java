package entities;

public class PhysicalProduct implements IPhysicalProduct {
    private final String name;
    private final float price;
    private int stock;

    public PhysicalProduct(String name, float price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String getName() { return name; }

    @Override
    public float getPrice() { return price; }

    @Override
    public void checkStock(int quantity) throws Exception {
        if (stock < quantity) {
            throw new Exception("Stock insuffisant pour " + name);
        }
    }

    @Override
    public void deductStock(int quantity) throws Exception {
        checkStock(quantity);
        stock -= quantity;
    }

    @Override
    public String getStockDisplay() {
        return String.valueOf(stock);
    }

    @Override
    public float calculateShippingCost() {
        return 5.99f;
    }

    @Override
    public String toString() {
        return "Produit : " + name + " | Prix : " + price + "€ | Stock : " + getStockDisplay();
    }
}
