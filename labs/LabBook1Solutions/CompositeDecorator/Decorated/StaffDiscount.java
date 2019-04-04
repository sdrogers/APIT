public class StaffDiscount extends AbstractShopDecorator {
    public StaffDiscount(ShopComponent component) {
        super(component);
    }
    public Double compPrice() {
        return this.component.compPrice() * 0.5;
    }
    public String toString() {
        return this.component.getName() + String.format(" (£%5.2f)",compPrice()) + " (with staff discount) ";
    }
}