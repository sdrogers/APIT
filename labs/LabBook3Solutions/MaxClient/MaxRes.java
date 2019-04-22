// A class for returning the results -- returns the
// max (a double) and the position that the result
// should be stored in
import java.io.Serializable;
public class MaxRes implements Serializable {
    private int pos;
    private Double max;
    public MaxRes(int pos,Double max) {
        this.pos = pos;
        this.max = max;
    }
    public Double getMax() {
        return max;
    }
    public int getPos() {
        return pos;
    }
}