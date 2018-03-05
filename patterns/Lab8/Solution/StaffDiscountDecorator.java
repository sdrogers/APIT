public class StaffDiscountDecorator extends ShopComponentDecorator {
	// A staff discount decorator
	// They get a 0.5 discount on the price
	// of whatever object they wrap
	public StaffDiscountDecorator(ShopComponent decoratedComponent) {
		super(decoratedComponent);
	}
	public String toString() {
		return "" + super.toString() + "(staff discount)";
	}
	public Double compPrice() {
		return 0.5*super.compPrice(); // Staff get 50%
	}
}