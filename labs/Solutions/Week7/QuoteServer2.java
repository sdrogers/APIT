// Server for the second part of Week7 lab.
// See QuoteClient2.java for the client code (identical to Quoteclient1)
// This extension justs allows for multiple connections

import java.util.ArrayList;
import java.net.*;
import java.io.*;
import java.util.Random;
public class QuoteServer2 {
	public static final int PORT = 8765;
	public static ArrayList<String> quoteList = QuoteLoader.loadQuotes("quotes.txt");
	
	public static class Handler extends Thread{
		private final Socket client;
		private final Random r = new Random();
		public Handler(Socket client) {
			this.client = client;
		}
		public void run() {
			PrintWriter writer;
			try {
				writer = new PrintWriter(client.getOutputStream(),true);
				while(true) {
					// Choose a random quote and send
					int quotePos = r.nextInt(quoteList.size());
					writer.println(quoteList.get(quotePos));	
					// Wait for 5 seconds
					try {
						Thread.sleep(5000);
					}catch(InterruptedException e) {
						e.printStackTrace();
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