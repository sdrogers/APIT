// The shared buffer - the chef waits for the waiter to have space on 
// his/her tray and then places a dish on it
// the customer waits for a dish on the waiter and then takes it off and consumes it
import java.util.concurrent.locks.*;
public class Waiter {
	private ReentrantLock waiterLock = new ReentrantLock();
	private Condition waiterCondition = waiterLock.newCondition();
	private boolean hasDish = false;
	private String dish;
	public void giveDish(String dish) {
		// called by the chef
		waiterLock.lock();
		try {
			while(this.hasDish) {
				waiterCondition.await();
			}
			// when we get to here, the previous dish has gone so we can add a new one
			this.dish = dish;
			System.out.println("Chef has given the waiter the " + this.dish);
			this.hasDish = true;
			// Alert all the waiting things (the customer)
			waiterCondition.signalAll();

		}catch(InterruptedException e){
			e.printStackTrace();
		}finally {
			waiterLock.unlock();
		}
	}

	// Called by the customer
	public String takeDish() {
		waiterLock.lock();
		try{
			// Wait until there is a dish
			while(!this.hasDish) {
				waiterCondition.await();
			}
			// when we get here, there is a dish
			this.hasDish = false;
			System.out.println("Waiter has given " + this.dish + " to the diner");
			waiterCondition.signalAll();

		}catch(InterruptedException e) {
			e.printStackTrace();
		}finally{
			waiterLock.unlock();
		}
		return this.dish;
	}

}