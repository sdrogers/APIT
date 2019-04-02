// Class that goes at the end. It extends segment
// so that it can be assigned to the 'next' attribute of the last
// visible track
public class TrainSink extends Segment {
    // These go at the end and trains disappear...
    public TrainSink() {
        super(0,0,"");
    }    
    // Override enter and leave, as we don't 
    // care what happens when trains enter here
    // or when they leave

    public void enter(Train t) {
        // nothing
    }
    public void leave(Train t) {
        // nothing
    }

    // This is invisible...
    public String toString() {
        return "";
    }
}