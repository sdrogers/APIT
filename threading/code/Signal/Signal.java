// A programme to demonstrate the difference between signal and signalAll
// Multiple instances of the Waiting thread go into the shared object
// and call await while the 'ready' variable is false

// After main takes a short sleep, a SetReady object is created which called the 
// setReady method of Shared and, in doing so, sets 'ready' to true and then
// either calls Condition.signal() or Condition.signalAll().

// In the former case, one thread is woken, realises that ready is true and finishes.
// All other threads remain asleep and the programme hangs

// In the latter case, all threads are woken, and will obtain the lock one by one (as each one releases it)
// and will all finish

import java.util.concurrent.locks.*;
public class Signal {
	public static class Shared {
		private ReentrantLock r = new ReentrantLock();
		private Condition c = r.newCondition();
		private boolean ready = false;
		public void in() {
			r.lock();
			try {
				while(!ready) {
					c.await();
				}
			}catch(InterruptedException e) {
			}finally {
				r.unlock();
			}
			System.out.println("Finished!");
		}
		public void setReady() {
			r.lock();
			try {	
				ready = true;
				c.signal(); //Toggle this and the next line
				// c.signalAll();
			}finally {
				r.unlock();
			}
		}
	}
	public static class Waiting extends Thread {
		private Shared s;
		public Waiting(Shared s) {
			this.s = s;
		}
		public void run() {
			s.in();
		}
	}
	public static class SetReady extends Thread {
		private Shared s;
		public SetReady(Shared s) {
			this.s = s;
		}
		public void run() {
			s.setReady();
		}
	}
	public static void main(String[] args) {
		Shared s = new Shared();
		int nThreads = 10;
		Waiting[] g = new Waiting[nThreads];
		for(int i = 0;i<nThreads;i++) {
			g[i] = new Waiting(s);
			g[i].start();
		}

		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {}

		new SetReady(s).start();
	}
}