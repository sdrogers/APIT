public class ShopItem implements ShopComponent {
	private final String name;
	private final double price;
	public ShopItem(String n, double p) {
		name = n;
		price = p;
	}
	public double compPrice() {
		return price;
	}
	public String toString() {
		return name + ": " + String.format("%.2f",compPrice());
	}
}