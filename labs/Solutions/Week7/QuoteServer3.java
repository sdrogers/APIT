// Server for the final parts of Week7 lab.
// This extension responds to client requests
// Note that for the admin stuff, you could create a different server
// socket on a different port etc so that only admin could connect

import java.util.ArrayList;
import java.net.*;
import java.io.*;
import java.util.Random;
public class QuoteServer3 {
	public static final int PORT = 8765;
	public static ArrayList<String> quoteList = QuoteLoader.loadQuotes("quotes.txt");
	public static Integer nClients = 0;
	public static Integer nRequests = 0;
	public static class Handler extends Thread{
		private final Socket client;
		private final Random r = new Random();
		public Handler(Socket client) {
			this.client = client;
			nClients += 1;
		}
		public void run() {
			PrintWriter writer;
			BufferedReader reader;
			try {
				writer = new PrintWriter(client.getOutputStream(),true);
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				while(true) {
					// Choose a random quote and send
					String line = reader.readLine();
					if(line == null) {
						nClients --;
						return;
					}
					if(line.equals("MOTIVATEME")) {
						int quotePos = r.nextInt(quoteList.size());
						writer.println(quoteList.get(quotePos));
						nRequests += 1;						
					}else if(line.startsWith("ADDQUOTE:")) {
						quoteList.add(line.substring(9));
						System.out.println("Added " + line.substring(9));
						writer.println("THANKS");
					}else if(line.startsWith("NREQUESTS")) {
						writer.println("There have been " + nRequests + " motivational moments");
					}else if(line.startsWith("NCLIENTS")) {
						writer.println("There are " + nClients + " online");
					}else if(line.startsWith("NQUOTES")) {
						writer.println("The system contains " + quoteList.size() + " wonderful quotes");
					}else{
						writer.println("ERROR");
					}
				}
			}catch(IOException e) {
				e.printStackTrace();	
			}finally {
				// Good practice to close the client and server
				// within the finally block to ensure it happens.
				// This means you'll need another try catch block here.
				try {
					if(client != null) {
						client.close();
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) {
		// Load the quotes...
		ArrayList<String> quoteList = QuoteLoader.loadQuotes("quotes.txt");
		ServerSocket server = null;
		Socket client = null;
		Random r = new Random();
		try {
			server = new ServerSocket(PORT);
			// Wait for the client...
			while(true) {
				new Handler(server.accept()).start();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}