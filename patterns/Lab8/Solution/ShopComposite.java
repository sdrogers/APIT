import java.util.ArrayList;

public class ShopComposite implements ShopComponent {
	// The composite class
	private String name;
	// The array list can include anything that implements
	// ShopComponent: leaves, composites, and decorated
	// leaves and composites
	private ArrayList<ShopComponent> children;
	public ShopComposite(String name) {
		this.name = name;
		children = new ArrayList<ShopComponent>();
	}
	public String toString() {
		String s = name + " {";
		for(ShopComponent c : children) {
			s += c + ",";
		}
		s+="}";
		return s;
	}
	public Double compPrice() {
		Double price = 0.0;
		for(ShopComponent c: children) {
			price += c.compPrice();
		}
		return price;
	}
	// Need add and remove methods
	// to add objects to the composite
	public void add(ShopComponent c) {
		children.add(c);
	}
	public void remove(ShopComponent c) {
		children.remove(c);
	}
}