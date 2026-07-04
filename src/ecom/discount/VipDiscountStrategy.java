package ecom.discount;

public class VipDiscountStrategy implements IDiscountStrategy {
    @Override
    public float applyDiscount(float subtotal) {
        return subtotal * 0.80f;
    }
}
