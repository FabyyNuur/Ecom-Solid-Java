package services;

import entities.order.Order;
import entities.order.OrderLine;

public class StockService implements IStockService {
    @Override
    public void checkAvailability(Order order) {
        for (OrderLine line : order.getLines()) {
            line.getProduct().checkStock(line.getQuantity());
        }
    }

    @Override
    public void deductStock(Order order) {
        for (OrderLine line : order.getLines()) {
            line.getProduct().deductStock(line.getQuantity());
        }
    }
}
