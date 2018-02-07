public class Diner extends Thread {
	private Waiter waiter;
	private boolean hasDish = false;
	private String dish;
	public Diner(Waiter waiter) {
		this.waiter = waiter;
	}
	public void run() {
		while(true) {
			this.dish = waiter.takeDish();
			System.out.println("Diner is consuming " + this.dish);
			try{
				Thread.sleep(5000); // time to eat it
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Diner has finished consuming " + this.dish);
		}
	}
}