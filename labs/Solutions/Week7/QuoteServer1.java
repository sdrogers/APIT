// Server for first part of Week7 lab.
// See QuoteClient1.java for the client code

import java.util.ArrayList;
import java.net.*;
import java.io.*;
import java.util.Random;
public class QuoteServer1 {
	public static final int PORT = 8765;
	public static void main(String[] args) {
		// Load the quotes...
		ArrayList<String> quoteList = QuoteLoader.loadQuotes("quotes.txt");
		ServerSocket server = null;
		Socket client = null;
		Random r = new Random();
		try { // all server and client operations need to be in a try catch block
			server = new ServerSocket(PORT);
			// Wait for the client...
			client = server.accept();
			// Create a printWriter for the client
			PrintWriter writer = new PrintWriter(client.getOutputStream(),true);
			// We are now connected
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
				if(server != null) {
					server.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}