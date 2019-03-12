import java.util.Random;
import java.util.Iterator;
public class RandomIterator implements Iterable<Double> {
	private Random r = new Random();
	public Iterator<Double> iterator() {
		return new Iterator<Double>() {
			public Double next() {
				return r.nextDouble();
			}
			public void remove() {
				throw new UnsupportedOperationException();
			}
			public boolean hasNext() {
				return true;
			}
		};
	}
	public static void main(String[] args) {
		RandomIterator ri = new RandomIterator();
		for(Double d: ri) {
			System.out.println(d);
		}
	}
}
