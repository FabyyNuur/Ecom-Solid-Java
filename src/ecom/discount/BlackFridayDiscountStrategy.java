package ecom.discount;

public class BlackFridayDiscountStrategy implements IDiscountStrategy {
    @Override
    public float applyDiscount(float subtotal) {
        return subtotal * 0.50f;
    }
}
