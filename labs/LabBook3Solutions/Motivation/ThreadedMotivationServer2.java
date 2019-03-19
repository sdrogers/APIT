import java.util.ArrayList;
import java.net.*;
import java.io.*;
import java.util.Random;
public class ThreadedMotivationServer2 {

	private static class ClientThread extends Thread {
		private Socket client;
		private PrintWriter writer;
		private ArrayList<String> quotes;
		private BufferedReader reader;
		private Random r = new Random();
		public ClientThread(Socket client,ArrayList<String> quotes) {
			this.client = client;
			this.quotes = quotes;
			try {
				writer = new PrintWriter(this.client.getOutputStream(),true);
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		public void run() {
			while(true) {
				// try {
				// 	Thread.sleep(5000);
				// }catch(InterruptedException e) {
				// 	e.printStackTrace();
				// }
				try {
					String line = reader.readLine();
					if(line.equals("MOTIVATEME")) {
						int quoteNumber = r.nextInt(quotes.size());
						writer.println(quotes.get(quoteNumber));
					}else if(line.startsWith("ADD QUOTE:")) {
						String[] tokens = line.split(":");
						synchronized(quotes) {
							quotes.add(tokens[1]);
						}
						writer.println("thanks!");
					}else {
						writer.println("LEARN TO TYPE");
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
				
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