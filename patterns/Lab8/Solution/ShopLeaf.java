public class ShopLeaf implements ShopComponent {
	// The shop leaf object
	private String name;
	private Double price;
	public ShopLeaf(String name,Double price) {
		this.name = name;
		this.price = price;
	}
	// Implements the methods defined in ShopComponent
	public String toString() {
		return name;
	}
	public Double compPrice() {
		return price;
	}
} 