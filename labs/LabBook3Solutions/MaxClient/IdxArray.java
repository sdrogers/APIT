// A class so that we can pass an array and an integer
// through a stream. The array is the array to be sorted
// and the integer is the position that the client
// should return so the server knows which one it was
import java.io.Serializable;
public class IdxArray implements Serializable{
    private Double[] a;
    private int pos;
    public IdxArray(int pos, Double[] a) {
        this.a = a;
        this.pos = pos;
    }
    public Double[] getA() {
        return a;
    }
    public int getPos() {
        return pos;
    }

}