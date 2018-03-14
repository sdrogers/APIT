public class StudentDiscount extends ShopDecorator {
	public StudentDiscount(ShopComponent decoratedComponent) {
		super(decoratedComponent);
	}
	public double compPrice() {
		return 0.9*super.compPrice();
	}
	public String toString() {
		return "[" + super.toString() + " STUDENT DISCOUNT APPLIED: " + String.format("%.2f",compPrice()) + "]";
	}
}