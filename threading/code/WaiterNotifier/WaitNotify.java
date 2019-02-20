public class WaitNotify {
    public static void main(String[] args) {
        Object shared = new Object();
        new Thread(new Waiter(shared),"Waiter 1").start();
        new Thread(new Waiter(shared),"Waiter 2").start(); // make another
        
        new Thread(new Notifier(shared),"Notifier").start();

    }
}