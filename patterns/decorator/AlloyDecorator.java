public class AlloyDecorator extends CarDecorator {
	public AlloyDecorator (Car decoratedCar) {
		super(decoratedCar); // Call the CarDecorator constructor
	}
	public Double getPrice() {
		return super.getPrice() + 250; // Add the price of alloys
	}
	public String getDescription() {
		return super.getDescription() + " + Alloys"; 
	}
}