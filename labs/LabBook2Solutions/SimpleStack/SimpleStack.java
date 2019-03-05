import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;

/*
    An implementation of a stack that has methods to add and remove items
    It simulates the stack that might be used to store 
    processing jobs. In this example, the processing jobs are represented as ints
    which are interpreted as the length of time the job would take to run
*/

/*
    When something is added, the lock is locked
    When something is removed, the lock is locked and the 
    thread trying to remove sleeps until something has appeared
*/
public class SimpleStack {
    private ArrayList<Integer> a = new ArrayList<Integer>();
    int pos = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();
    // Get the last 'job' that was added
    public Integer getJob() {
        lock.lock();
        while(pos == 0) { // no jobs
            try {
                // Thread.sleep(1);
                cond.await();
                System.out.println(Thread.currentThread().getName() + " is waiting");
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        pos -= 1;
        Integer val = a.get(pos);
        a.remove(pos);
        lock.unlock();
        return val;
    }
    // Add a job to the end
    public void addJob(Integer val) {
        lock.lock();
        a.add(val);
        pos += 1;
        cond.signal();
        lock.unlock();
    }
    // Useful method for printing
    public String toString() {
        String str = "Stack has " + pos + " jobs.";
        return str;
    }
}