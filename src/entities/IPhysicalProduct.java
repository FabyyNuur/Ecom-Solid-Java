package entities;


public interface IPhysicalProduct extends IProduct, IShippable {
    void checkStock(int quantity);
    void deductStock(int quantity);
    String getStockDisplay();
}
