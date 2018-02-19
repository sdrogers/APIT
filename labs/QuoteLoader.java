import java.util.ArrayList;
import java.io.*;
public class QuoteLoader {
	public static ArrayList<String> loadQuotes(String filename) {
		ArrayList<String> q = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while((line = reader.readLine())!=null) {
				q.add(line);
			}
			reader.close();
			return q;
		}catch(IOException e) {}
		return q;
	}
}