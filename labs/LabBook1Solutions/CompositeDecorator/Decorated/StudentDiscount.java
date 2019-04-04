public class StudentDiscount extends AbstractShopDecorator {
    public StudentDiscount(ShopComponent component) {
        super(component);
    }
    public Double compPrice() {
        return this.component.compPrice() * 0.9;
    }
    public String toString() {
        return this.component.getName() + String.format(" (£%5.2f)",compPrice()) + " (with student discount) ";
    }
}