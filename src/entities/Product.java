package entities;


public abstract class Product {
    protected String name;
    protected float price;
    // protected final ProductType type;
    

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
        // this.type= type;
    }

    public String getName() { return name; }
    public float getPrice() { return price; }

    public abstract void checkStock(int quantity) throws Exception;
    public abstract void deductStock(int quantity) throws Exception;
    public abstract String getStockDisplay();

    @Override
    public String toString() {
        return "Produit : " + name +  " | Prix : " + price + "€ | Stock : " + getStockDisplay();
    }
}