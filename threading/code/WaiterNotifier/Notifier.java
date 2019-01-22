public class Notifier implements Runnable {
    private Object shared;
    public Notifier(Object s) {
        shared = s;
    }
    public void run() {
        // Pause for a bit - perhaps simulating some calculation
        // that has to be performed before the Waiter
        // Threads can continue
        System.out.println(Thread.currentThread().getName() + " about to sleep");
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        synchronized(shared) {
            // shared.notify();
            System.out.println(Thread.currentThread().getName() + " about to notify!");
            shared.notifyAll();
        }
        
    }
}