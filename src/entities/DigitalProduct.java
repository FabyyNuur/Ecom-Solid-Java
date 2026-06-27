package entities;

public class DigitalProduct extends Product {

    public DigitalProduct(String name, float price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return "Produit : " + name + " | Prix : " + price + "€ | Stock : " + getStockDisplay();
    }
}
