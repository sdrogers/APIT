/*
    Object that can be placed on a thread to remove jobs from the 
    stack and then 'run' them (i.e. sleep for their duration).
*/

public class SimpleStackRemover implements Runnable {
    private SimpleStack ss;
    public SimpleStackRemover(SimpleStack ss) {
        this.ss = ss;
    }
    public void run() {
        while(true) {
            Integer val = ss.getJob();
            System.out.println(Thread.currentThread().getName() + " Got job");
            try {
                Thread.sleep(val);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}