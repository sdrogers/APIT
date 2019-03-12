import java.util.Iterator;
import java.util.Random;
public class DoublePositiveIterator implements Iterable<Double>{
	private int length;
	private Double[] d;
	private Random r = new Random();
	private int firstPositive = 0;
	public DoublePositiveIterator(int length) {
		this.length = length;
		d = new Double[this.length];
		for(int i=0;i<this.length;i++) {
			d[i] = r.nextDouble() - 0.5;
		}
		while(firstPositive < length) {
			firstPositive ++;
			if(d[firstPositive] > 0) {
				break;
			}
		}
	}
	public Iterator<Double> iterator() {
		return new Iterator<Double>() {
			private int pos = firstPositive;
			private boolean gotoNextPositive() {
				while(pos < length) {
					pos ++;
					if(pos == length) {
						break;
					}
					if(d[pos] > 0) {
						return true;
					}
				}
				return false;

			}
			public boolean hasNext() {
				if(pos < length) {
					return true;
				}else {
					return false;
				}
			}
			public Double next() {
				Double returnVal = d[pos];
				gotoNextPositive();
				return returnVal;
			}
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	public static void main(String[] args) {
		DoublePositiveIterator it = new DoublePositiveIterator(10);
		for(Double d: it) {
			System.out.println("Outer: " + d);
			for(Double e: it) {
				System.out.println("Inner: " + e);
			}			
		}
	}
}