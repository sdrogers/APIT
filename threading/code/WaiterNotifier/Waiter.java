public class Waiter implements Runnable {
    private Object shared;
    public Waiter(Object s) {
        shared = s;
    }
    public void run() {
        try {
            synchronized(shared) {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                shared.wait();
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " activated");
            
    }
}