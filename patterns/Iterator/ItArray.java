import java.util.Iterator;

public class ItArray implements Iterator {
	// Inside my iterable array, I'll have a standard array
	public Integer[] array;
	// Start at position zero
	int pos = 0;
	public ItArray(Integer[] array) {
		// Copies a reference to the array
		this.array = array;
	}
	// hasNext returns true if there are any items left
	public boolean hasNext() {
		if(pos<array.length) {
			return true;
		} else {
			return false;
		}
	}
	// return the next item (and increment the position)
	public Integer next() {
		return array[pos++];
	}
	// Remove it optional - you don't have to implement it but
	// you do have to define it. If you don't implement it, have it 
	// throw an exception.
	public void remove() {
		throw new UnsupportedOperationException();	
	}

}