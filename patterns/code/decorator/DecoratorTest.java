public class DecoratorTest {
	public static void main(String[] args) {
		Car base = new BasicCar();
		System.out.println("Car costs " + base.getPrice() + " and has " + base.getDescription());

		Car alloys = new AlloyDecorator(new BasicCar());
		System.out.println("Car costs " + alloys.getPrice() + " and has " + alloys.getDescription());		

		Car cd = new CDDecorator(new BasicCar());
		System.out.println("Car costs " + cd.getPrice() + " and has " + cd.getDescription());	

		Car all = new CDDecorator(new AlloyDecorator(new BasicCar()));
		System.out.println("Car costs " + all.getPrice() + " and has " + all.getDescription());

		Car cd2 = new CDDecorator(new LuxuryCar());
		System.out.println("Car costs " + cd2.getPrice() + " and has " + cd2.getDescription());	

	}
}