import java.util.Random;

/*
    A class that randomly creates 'jobs'
    Each job is an integer which is interpreted as
    How long it takes to run.
    After making each job, this thread sleeps for a second.
*/

public class StackPopulator implements Runnable {
    private SimpleStack stack;
    private Random r = new Random();
    public StackPopulator(SimpleStack stack) {
        this.stack = stack;
    }
    public void run() {
        while(true) {
            Integer nextVal = r.nextInt(1000);
            stack.addJob(nextVal);
            System.out.println(stack);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

    // Main method for the program to demonstrate the deadlock
    public static void main(String[] args) {
        SimpleStack ss = new SimpleStack();
        new Thread(new StackPopulator(ss)).start();
        /*
            Sleep for an initial period before making the
            objects to remove items. This isn't necessary but allows
            the program to run for a while prior to the deadlock.
        */
        try {
            Thread.sleep(5000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new SimpleStackRemover(ss),"a").start();
        new Thread(new SimpleStackRemover(ss),"b").start();
    }
}