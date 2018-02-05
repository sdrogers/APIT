import java.util.ArrayList;
import java.util.Random;
public class LoadExample {
	public static void main(String[] args) {
		String filename = "quotes.txt";
		ArrayList<String> quotes = QuoteLoader.loadQuotes(filename);
		Random r = new Random();
		// Display 5 random quotes
		for(int i=0;i<5;i++) {
			Integer index = r.nextInt(quotes.size());
			System.out.println(quotes.get(index));
		}
	}
}