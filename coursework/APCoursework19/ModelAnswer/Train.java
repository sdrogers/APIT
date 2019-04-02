public class Train implements Runnable {
    private final int speed;
    private final String name;
    private Segment currentSegment;
    private Segment nextSegment;
    public Train(int speed,String name,Segment startPoint) {
        this.speed = speed;
        this.name = name;
        this.currentSegment = startPoint;
    }
    public void run() {
        while(true) {
            // Sleep based on current segment
            int sleepTime = currentSegment.getLength()/speed;
            try {
                Thread.sleep(sleepTime);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            // enter next segment
            nextSegment = currentSegment.getNext();

            // Check the condition that we're in the sink...
            if(nextSegment == null) {
                currentSegment.leave(this);
                break;
            }
            // If the we're not in the sink, enter the next
            nextSegment.enter(this);
            // Leave the current
            currentSegment.leave(this);
            // Update the current to the new one
            currentSegment = nextSegment;
        }
    }
    public String toString() {
        return this.name;
    }
}