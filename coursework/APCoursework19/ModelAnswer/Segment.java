import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
// Abstract class extended by both track, station, train maker, and train sink
// Main logic is in here, entering and leaving etc
public abstract class Segment {
    private Segment next = null;
    private final int length;
    private final int capacity;
    private final ArrayList<Train> trains;
    private final String name;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition cond = lock.newCondition();
    public Segment(int length, int capacity,String name) {
        this.length = length;
        this.capacity = capacity;
        trains = new ArrayList<Train>();
        this.name = name;
    }    
    // When a train enters, it 
    // 1. gets lock
    // 2. waits until space
    // 3. enters
    // 4. releases lock
    public void enter(Train t) {
        lock.lock();
        while(trains.size() == capacity) {
            try {
                cond.await();
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        trains.add(t);
        lock.unlock();
    }
    // when a train leaves it
    // 1. gets lock
    // 2. removes itself
    // 3. signals
    // 4. releases lock
    public void leave(Train t) {
        lock.lock();
        trains.remove(t);
        cond.signalAll();
        lock.unlock();
    }
    // Helper methods
    public void setNext(Segment next) {
        this.next = next;
    }
    public int getLength() {
        return length;
    }
    public Segment getNext() {
        return next;
    }

    // Make a string for this segment
    public String toString() {
        String output;
        output = String.format("|-- %s ",name);
        String trainString = "";
        for(Train t: trains) {
            trainString += String.format("%2s,",t.toString());
        }
        for(int i=0;i<capacity - trains.size();i++) {
            trainString += "   ";
        }
        output += trainString + "--|";
        return output;
    }
    public String getName() {
        return name;
    }
}