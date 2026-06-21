package entities;


public abstract class Product {
    protected String name;
    protected float price;
    private final ProductType productType;

    public Product(String name, float price, ProductType productType) {
        this.name = name;
        this.price = price;
        this.productType = productType;
    }

    public String getName() { return name; }
    public float getPrice() { return price; }
    public ProductType getProductType() { return productType; }

    public abstract void checkStock(int quantity) throws Exception;
    public abstract void deductStock(int quantity) throws Exception;
    public abstract String getStockDisplay();

    @Override
    public String toString() {
        return "Produit : " + name + " | Type : " + productType + " | Prix : " + price + "€ | Stock : " + getStockDisplay();
    }
}