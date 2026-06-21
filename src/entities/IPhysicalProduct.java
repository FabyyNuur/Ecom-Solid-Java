package entities;


public interface IPhysicalProduct extends IProduct {
    void checkStock(int quantity) throws Exception;
    void deductStock(int quantity) throws Exception;
    String getStockDisplay();
    float calculateShippingCost();
}
