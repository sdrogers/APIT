// The absract decorator. Note that this also implements ShopComponent
// i.e. both patterns share the same top level

public abstract class ShopDecorator implements ShopComponent {
	protected final ShopComponent decoratedComponent;
	public ShopDecorator(ShopComponent decoratedComponent) {
		this.decoratedComponent = decoratedComponent;
	}
	public double compPrice() {
		return decoratedComponent.compPrice();
	}
	public String toString() {
		return decoratedComponent.toString();
	}
}