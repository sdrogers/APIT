import java.util.ArrayList;

public class ShopComposite implements ShopComponent {
	private final String name;
	private final ArrayList<ShopComponent> children;
	public ShopComposite(String n) {
		name = n;
		children = new ArrayList<ShopComponent>();
	}
	public void addChild(ShopComponent c) {
		children.add(c);
	}
	public double compPrice() {
		double price = 0.0;
		for(ShopComponent s: children) {
			price += s.compPrice();
		}
		return price;
	}
	public String toString() {
		String st = name + ": " + String.format("%.2f",compPrice()) + " (";
		int pos = 0;
		for(ShopComponent s: children) {
			st += s;
			pos += 1;
			if(pos < children.size()) { // don't want a comma after the last one!
				st += ", ";
			}
		}
		st += ")";
		return st;
	}
}