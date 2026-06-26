package entities;


public interface IPhysicalProduct extends IShippable {
    void checkStock(int quantity);
    void deductStock(int quantity);
    String getStockDisplay();
}
