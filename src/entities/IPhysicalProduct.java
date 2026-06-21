package entities;


public interface IPhysicalProduct extends IProduct, IShippable {
    void checkStock(int quantity) throws Exception;
    void deductStock(int quantity) throws Exception;
    String getStockDisplay();
}
