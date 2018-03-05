public abstract class ShopComponentDecorator implements ShopComponent {
	// The abstract decorator. Has to be abstract as it needs a reference 
	// to a concrete ShopComponent object
	// It can decorate any ShopComponent object - could be a leaf, coulc
	// be a composite, could be another decorator

	// Note that the decorators all have to be passed a ShopComponent
	// object so we always need a component or leaf at the bottom
	private ShopComponent decoratedComponent;
	public ShopComponentDecorator(ShopComponent decoratedComponent) {
		this.decoratedComponent = decoratedComponent;
	}
	// Implements the methods in ShopComponent
	public Double compPrice() {
		return decoratedComponent.compPrice();
	}
	public String toString() {
		return "" + decoratedComponent;
	}
}