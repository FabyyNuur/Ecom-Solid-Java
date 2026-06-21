package services;

import entities.IPhysicalProduct;
import entities.Order;
import entities.OrderLine;

public class StockService {
    public void fulfill(Order order) throws Exception {
        for (OrderLine line : order.getLines()) {
            if (line.getProduct() instanceof IPhysicalProduct physical) {
                physical.checkStock(line.getQuantity());
            }
        }
        for (OrderLine line : order.getLines()) {
            if (line.getProduct() instanceof IPhysicalProduct physical) {
                physical.deductStock(line.getQuantity());
            }
        }
    }
}
