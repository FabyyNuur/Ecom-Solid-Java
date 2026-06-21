package services;

import entities.Order;
import entities.OrderLine;

public class StockService {
    public void fulfill(Order order) throws Exception {
        for (OrderLine line : order.getLines()) {
            line.getProduct().checkStock(line.getQuantity());
        }
        for (OrderLine line : order.getLines()) {
            line.getProduct().deductStock(line.getQuantity());
        }
    }
}
