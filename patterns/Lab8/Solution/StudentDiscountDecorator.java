public class StudentDiscountDecorator extends ShopComponentDecorator {
	// A Student discount decorator
	// They get a 0.9 discount on the price
	// of whatever object they wrap
	public StudentDiscountDecorator(ShopComponent decoratedComponent) {
		super(decoratedComponent);
	}
	public String toString() {
		return "" + super.toString() + "(student discount)";
	}
	public Double compPrice() {
		return 0.9*super.compPrice(); // Students get 10%
	}
}