// An example of making something
// that can deal with the 'new' for loop
// Note that it must implement Iterator *and* Iterable

import java.util.Iterator;

public class ItTest implements Iterator,Iterable<Integer>{
	private Integer[] a;
	private int pos;
	public ItTest(Integer[] a) {
		this.a = a;
		this.pos = 0;
	}
	public boolean hasNext() {
		if(this.pos < this.a.length) {
			return true;
		}else {
			return false;
		}
	}
	public Integer next() {
		return this.a[pos++];
	}

	public void remove() {
		throw new UnsupportedOperationException();	
	}

	public Iterator<Integer> iterator() {
		return this; // Could do this...
		// or this maybe better:
		// return new ItTest(this.a); // as it resets pos to zero
	}

	
	public static void main(String[] args) {
		Integer[] a = {1,2,3,4};
		ItTest c = new ItTest(a);
		for(Integer b: c) {
			for(Integer d: c) {
				System.out.println("" + b + " " + d);
			}
		}
		// for(Integer b: c) {
		// 	System.out.println(b);
		// }

	}
}