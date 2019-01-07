public abstract class CarDecorator extends Car {
	protected Car decoratedCar;
	public CarDecorator(Car decoratedCar) {
		this.decoratedCar = decoratedCar;
	}
	public Double getPrice() {
		return decoratedCar.getPrice();
	}
	public String getDescription() {
		return decoratedCar.getDescription();
	}
}