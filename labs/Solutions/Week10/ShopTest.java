public class ShopTest {
	public static void main(String[] args) {
		// Test the first part -- just the composite pattern
		ShopItem apple = new ShopItem("Apple",2.3);
		ShopItem banana = new ShopItem("Banana",1.8);
		System.out.println(apple);
		System.out.println(banana);

		ShopComposite basket = new ShopComposite("Basket");
		basket.addChild(apple);
		basket.addChild(banana);

		ShopItem bagel = new ShopItem("Bagel",1.2);
		ShopComposite bagels = new ShopComposite("Bagels");
		for(int i=0;i<3;i++) {
			bagels.addChild(bagel);
		}
		basket.addChild(bagels);


		System.out.println(basket);

		// Apply a staff discount
		System.out.println(new StaffDiscount(basket));

		// Apply a student discount
		System.out.println(new StudentDiscount(basket));

		// Apply both!
		System.out.println(new StudentDiscount(new StaffDiscount(basket)));		
	}
}