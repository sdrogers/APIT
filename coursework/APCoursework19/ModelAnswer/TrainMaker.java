import java.util.Random;
// Trainmaker
// Extends segment but doesn't worry about capacity (i.e. there
// can be as many trains waiting here as necessary).
// It overrides the leave method as nothing needs to be done
// when at train leaves here

public class TrainMaker extends Segment implements Runnable {
    // Makes trains that then try and enter 'next'
    private final Random r = new Random();
    private static int trainNumber = 0;
    public TrainMaker(Segment next) {
        super(0,0,"");
        super.setNext(next);
    }
    public void run() {
        while(true) {
            try {
                Thread.sleep(r.nextInt(1000));
                // System.out.println("Made train " + i);
                int speed = r.nextInt(2) + 1;
                // Create a new train passing 'this' as the starting segment 
                Train t = new Train(speed,""+trainNumber,this);
                new Thread(t).start();
                trainNumber += 1;
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void leave(Train t) {
        // do nothing
    }

}