public class CDDecorator extends CarDecorator {
	public CDDecorator (Car decoratedCar) {
		super(decoratedCar); // Call the CarDecorator constructor
	}
	public Double getPrice() {
		return super.getPrice() + 150; // Add the price of alloys
	}
	public String getDescription() {
		return super.getDescription() + " + CD Player"; 
	}
}