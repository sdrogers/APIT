import java.util.ArrayList;
public class ShopComposite implements ShopComponent {
	// This will store the leaves
	private ArrayList<ShopLeaf> children;
	private String name;
	// Constructor - create the list and set the name
	public ShopComposite(String n) {
		children = new ArrayList<ShopLeaf>();
		name = n;
	}
	// Composites normally delegate the methods to the leaves
	public Double compPrice(Double discount) {
		Double price = 0.0;
		// arraylists can be iterated...
		for(ShopLeaf a: children) {
			price += a.compPrice(discount);
		}
		return price;
	}
	// Add and remove just call the arraylist methods
	public void add(ShopLeaf a) {
		children.add(a);
	}
	public void remove(ShopLeaf a) {
		children.remove(a);
	}
	// A nice toString method that displays the composite name
	// and the children names
	public String toString() {
		String totalString = name;
		totalString += " {";
		for(ShopLeaf a: children) {
			// Invokes toString on children..
			totalString += a + ",";
		}
		totalString += "}";
		return totalString;
	}
}