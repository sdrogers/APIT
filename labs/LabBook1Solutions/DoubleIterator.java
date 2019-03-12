import java.util.Random;
import java.util.Iterator;
import java.util.ArrayList;
public class DoubleIterator implements Iterable<Double>{
	private int length;
	private Double[] d;
	private Random r = new Random();
	// private int pos;
	public DoubleIterator(int length) {
		this.length = length;
		d = new Double[this.length];
		for(int i=0;i<this.length;i++) {
			d[i] = r.nextDouble() - 0.5;
		}
		// pos = 0;
	}
	// public void remove() {
	// 	throw new UnsupportedOperationException();
	// }
	// public boolean hasNext() {
	// 	if(pos < length) {
	// 		return true;
	// 	}else {
	// 		return false;
	// 	}
	// }
	// public Double next() {
	// 	return d[pos++];
	// }
	public Iterator<Double> iterator() {
		// return this;
		return new Iterator<Double>() {
			private int pos = 0;
			public boolean hasNext() {
				if(pos < length) {
					return true;
				}else {
					return false;
				}
			}
			public Double next() {
				return d[pos++];
			}
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	public static void main(String[] args) {
		DoubleIterator it = new DoubleIterator(10);
		// Iterator<Double> b = it;
		// while(it.hasNext()) {
		// 	System.out.println(it.next());
		// }

		// while(it.hasNext()) {
		// 	System.out.println(it.next());
		// }

		for(Double d: it) {
			System.out.println("Outer: " + d);
			for(Double e: it) {
				System.out.println("Inner: " + e);
			}			
		}
	}
}