package entities;


public class DigitalProduct extends Product {
    public DigitalProduct(String name, float price) {
        super(name, price, ProductType.DIGITAL);
    }

    @Override
    public void checkStock(int quantity) {
    }

    @Override
    public void deductStock(int quantity) {
    }

    @Override
    public String getStockDisplay() {
        return "∞";
    }
}
