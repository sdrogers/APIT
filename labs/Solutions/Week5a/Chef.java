// The chef class...it passes dishes to the waiter
public class Chef extends Thread {
	private Waiter waiter;
	private String[] dishes = {"Starter","Main","Pudding","Coffee","Wafer thin mint"};
	public Chef(Waiter waiter) {
		this.waiter = waiter;
	}
	public void run() {
		for(String d: dishes) {
			try {
				Thread.sleep(3000); // time taken to prepare a course
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Chef has finished preparing " + d);
			waiter.giveDish(d);
		}
	}
}