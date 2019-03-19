import java.util.ArrayList;
import java.net.*;
import java.io.*;
import java.util.Random;
public class ThreadedMotivationServer {

	private static class ClientThread extends Thread {
		private Socket client;
		private PrintWriter writer;
		private ArrayList<String> quotes;
		private Random r = new Random();
		public ClientThread(Socket client,ArrayList<String> quotes) {
			this.client = client;
			this.quotes = quotes;
			try {
				writer = new PrintWriter(this.client.getOutputStream(),true);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		public void run() {
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


	public static void main(String[] args) throws IOException {
		ArrayList<String> quotes = QuoteLoader.loadQuotes("quotes.txt");
		ServerSocket server = new ServerSocket(8765);
		while(true) {
			Socket client = server.accept();
			new ClientThread(client,quotes).start();	
		}
		
	}
}