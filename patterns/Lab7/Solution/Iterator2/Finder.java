import java.util.Iterator;

public class Finder {
	// Completely generic find method - can take any object and any iterator
	// Note the generic in the method declaration - it says that this method
	// can be used with any type that implements comparable.
	public static <T extends Comparable<T>> T findBiggest(Iterator<T> b) {
		// Start by setting the biggest to the first one
		T biggest = b.next();
		int pos = 1;
		while(b.hasNext()) {
			T current = b.next();
			if(biggest.compareTo(current)<0) {
				biggest = current;
			}
			pos++;
		}
		return biggest;
	}


	public static void main(String[] args) {
		// Create an example ItArray object
		Integer[] test = new Integer[5];
		test[0] = 1;test[1] = 2;test[2] = 7;test[3] = 4;test[4] = 5;
		ItArray<Integer> ia = new ItArray<Integer>(test);


		// Try and find biggest
		Integer biggest = findBiggest(ia);
		System.out.println(biggest);

		String[] stringTest = new String[4];
		stringTest[0] = "Hello";
		stringTest[1] = "I am";
		stringTest[2] = "ZZ";
		stringTest[3] = "Simon";
		ItArray<String> ib = new ItArray<String>(stringTest);
		String biggestString = findBiggest(ib);
		System.out.println(biggestString);

		
	}
}