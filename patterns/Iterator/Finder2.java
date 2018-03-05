import java.util.Iterator;

public class Finder2 {
	// Completely generic find method
	public static int find(Object obj,Iterator b) {
		int pos = 0;
		while(b.hasNext()) {
			if(obj == b.next()) {
				return pos;
			}
			pos++;
		}
		return -1;
	}


	public static void main(String[] args) {
		Integer[] test = new Integer[5];
		test[0] = 1;test[1] = 2;test[2] = 3;test[3] = 4;test[4] = 5;
		ItArray ia = new ItArray(test);
		Integer toFind = 7;
		int pos = find(toFind,ia);
		System.out.println(pos);

		ItCollection ib = new ItCollection(test);
		for(Integer i: ib) {
			System.out.println(i);
		}
	}
}