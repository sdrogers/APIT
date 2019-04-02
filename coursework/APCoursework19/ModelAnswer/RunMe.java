public class RunMe {
    public static void main(String[] args) {
        // Make the railway and things here - could put them in a separate class
        Station glasgow = new Station("Glasgow",5);
        Track t1 = new Track(2000);
        Station stirling = new Station("Stirling",2);
        Track t2 = new Track(3000);
        Station perth = new Station("Perth",3);
        glasgow.setNext(t1);

        // All Segment objects have a 'next' attribute
        // that points to the next segment (track or station)
        t1.setNext(stirling);    
        stirling.setNext(t2);
        t2.setNext(perth);

        // Finish the line with a sink
        perth.setNext(new TrainSink());

        // Make a trainmaker that injects trains into Glasgow
        new Thread(new TrainMaker(glasgow)).start();
        // new Thread(new TrainMaker(stirling)).start();
        // Make a printer - it just needs the first segment, 
        // and iterates through using the 'next' methods
        new Thread(new RailwayPrinter(glasgow)).start();
    }
}