import java.util.Random;
import java.util.Iterator;
public class TenRandoms implements Iterable<Double> {
    private Random r;
    private Double[] theNumbers;
    public TenRandoms(int howMany) {
        theNumbers = new Double[howMany];
        r = new Random();
        for(int i=0;i<10;i++) {
            theNumbers[i] = r.nextDouble();
        }
    }
    public Iterator<Double> iterator() {
        Iterator<Double> it = new Iterator<Double>() {            
            private int pos = 0;
            public boolean hasNext() {
                if(pos < theNumbers.length) {
                    return true;
                }
                return false;
            }
            public Double next() {
                return theNumbers[pos++];
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
    

    public static void main(String[] args) {
        TenRandoms tr = new TenRandoms(10);
        Iterator it = tr.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        /*
         Iterable also allows you to use Java's concise
         for loop syntax
        */
        System.out.println();
        for(Double r : tr) {
            System.out.println(r);
        }
    }
}