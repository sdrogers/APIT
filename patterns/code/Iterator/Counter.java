import java.util.Iterator;
public class Counter implements Iterator<Integer>{
    int pos;
    public Counter() {
        pos = 0; // Start at 0
    }
    public Integer next() {
        return pos++;
    }
    public boolean hasNext() {
        if(pos < 10) {
            return true;
        }else {
            return false;
        }
    }
    public void remove() {
        throw new UnsupportedOperationException();	
    }
    public static void main(String[] args) {
        Counter c = new Counter();
        while(c.hasNext()) {
            System.out.println(c.next());
        }
    }
}