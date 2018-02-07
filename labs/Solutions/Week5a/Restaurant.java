public class Restaurant {
	public static void main(String[] args) {
		Waiter w = new Waiter();
		Chef c = new Chef(w);
		Diner d = new Diner(w);
		c.start();
		d.start();
	}
}