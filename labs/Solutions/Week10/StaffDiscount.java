public class StaffDiscount extends ShopDecorator {
	public StaffDiscount(ShopComponent decoratedComponent) {
		super(decoratedComponent);
	}
	public double compPrice() {
		return 0.5*super.compPrice();
	}
	public String toString() {
		return "[" + super.toString() + " STAFF DISCOUNT APPLIED: " + String.format("%.2f",compPrice()) + "]";
	}
}