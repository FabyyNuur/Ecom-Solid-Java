package services;

import entities.Product;

public class InventoryManager {
    public void checkStock(Product product, int quantity) throws Exception {
        if (product.stock < quantity) {
            throw new Exception("Stock insuffisant pour " + product.name);
        }
    }

    public void deductStock(Product product, int quantity) {
        product.stock -= quantity;
    }
}
