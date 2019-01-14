import java.util.concurrent.locks.ReentrantLock;

public  class SharedDoubleLock {
    private Double d;
    private ReentrantLock l = new ReentrantLock();
    public void lock() {
        l.lock();
    }
    public void unlock() {
        l.unlock();
    }
    public Double getD() {
        return d;
    }
    public void setD(Double d) {
        this.d = d;
    }
}