public class Main {
	public static void main(String[] args) {
		Leaf banana = new Leaf(23);
		Leaf apple = new Leaf(37);
		Leaf car = new Leaf(203);

		Composite multiApple = new Composite();
		for(int i=0;i<6;i++) {
			multiApple.add(apple);
		}

		Composite multiMultiApple = new Composite();
		for(int i=0;i<4;i++) {
			multiMultiApple.add(multiApple);
		}


		Composite basket = new Composite();
		basket.add(banana);
		basket.add(multiMultiApple);
		basket.add(car);

		System.out.println(basket.getNumber());
		System.out.println(basket.getPrice());
	}
}