import java.util.ArrayList;
import java.net.*;
import java.io.*;
import java.util.Random;
public class MotivationServer {
	public static void main(String[] args) throws IOException {
		Random r = new Random();
		ArrayList<String> quotes = QuoteLoader.loadQuotes("quotes.txt");
		ServerSocket server = new ServerSocket(8765);
		Socket client = server.accept();

		PrintWriter writer = new PrintWriter(client.getOutputStream(),true);
		while(true) {
			try {
				Thread.sleep(5000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			int quoteNumber = r.nextInt(quotes.size());
			writer.println(quotes.get(quoteNumber));
		}
	}
}