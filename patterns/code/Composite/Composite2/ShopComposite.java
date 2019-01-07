import java.util.ArrayList;
public class ShopComposite implements ShopComponent {
	// This will store the leaves
	private ArrayList<ShopComponent> children;
	private String name;
	// Constructor - create the list and set the name
	public ShopComposite(String n) {
		children = new ArrayList<ShopComponent>();
		name = n;
	}
	// Composites normally delegate the methods to the leaves
	public Double compPrice(Double discount) {
		Double price = 0.0;
		// arraylists can be iterated...
		for(ShopComponent a: children) {
			price += a.compPrice(discount);
		}
		return price;
	}
	// Add and remove just call the arraylist methods
	public void add(ShopComponent a) {
		children.add(a);
	}
	public void remove(ShopComponent a) {
		children.remove(a);
	}
	// A nice toString method that displays the composite name
	// and the children names
	public String toString() {
		String totalString = name;
		totalString += " {";
		for(ShopComponent a: children) {
			// Invokes toString on children..
			totalString += a + ",";
		}
		totalString += "}";
		return totalString;
	}
}