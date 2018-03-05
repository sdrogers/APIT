import java.util.Iterator;

public class ItCollection implements Iterable<Integer> {
	public Integer[] array;
	public ItCollection(Integer[] array) {
		this.array = array;
	}
	public Iterator<Integer> iterator() {
		return new ItArray(array);
	}
}