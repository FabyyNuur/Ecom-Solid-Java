package entities;

public class PhysicalProduct extends Product implements IPhysicalProduct {
    private int stock;

    public PhysicalProduct(String name, float price, int stock) {
        super(name, price);
        this.stock = stock;
    }

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
