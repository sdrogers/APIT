public class RailwayPrinter implements Runnable {
    // Class that defines a railway printer
    // This is the 'view' in the MVC world
    // Implements runnable
    private Segment start;
    public RailwayPrinter(Segment start) {
        this.start = start;
    }    
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000); // prints once per second
                String output = "";
                Segment current = start;
                do {
                    // Synch block avoids concurrent arraylist modification errors
                    synchronized(current) {
                        output += current.toString();
                        current = current.getNext();
                    }
                }while(current.getNext() != null);
                System.out.println(output);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}