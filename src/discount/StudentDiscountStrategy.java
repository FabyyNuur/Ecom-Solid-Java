package discount;

public class StudentDiscountStrategy implements IDiscountStrategy {
    @Override
    public float applyDiscount(float subtotal) {
        return subtotal * 0.90f;
    }
}
