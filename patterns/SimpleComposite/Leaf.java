public class Leaf implements Component {
	private int price;
	public Leaf(int price) {
		this.price = price;
	}
	public int getNumber() {
		return 1;
	}
	public int getPrice() {
		return price;
	}
}