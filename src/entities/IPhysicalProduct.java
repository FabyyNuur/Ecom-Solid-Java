package entities;


public interface IPhysicalProduct extends IProduct {
    void checkStock(int quantity);
    void deductStock(int quantity);
    String getStockDisplay();
    float calculateShippingCost();
}
