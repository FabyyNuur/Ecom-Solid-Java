package services;

import entities.IPhysicalProduct;
import entities.order.Order;
import entities.order.OrderLine;

public class StockService {
    public void fulfill(Order order) {
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
