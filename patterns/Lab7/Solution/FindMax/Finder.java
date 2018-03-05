import java.util.Iterator;

public class Finder {
	
	public static Integer findBiggest(Iterator b) {
		// Start by setting the biggest to the first one
		// then iterate over them setting biggest if the 
		// current value is bigger than the current
		// biggesr
		Integer biggest = (Integer)b.next();
		int pos = 1;
		while(b.hasNext()) {
			Integer current = (Integer)b.next();
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
		ItArray ia = new ItArray(test);


		// Try and find biggest
		Integer biggest = findBiggest(ia);
		System.out.println(biggest);

		
	}
}