import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class MotivateServer {
	private static int PORT = 8765;
	public static void main(String[] args) throws IOException {	
		// Create the server socket
		ServerSocket serverSocket = new ServerSocket(PORT);
		// Load the motivational quotes
		ArrayList<String> quotes = QuoteLoader.loadQuotes("quotes.txt");
		// Create a random object
		Random r = new Random();
		// Wait for a connection from a client
		Socket client = serverSocket.accept();
		// Create the reader and writer
		PrintWriter out = new PrintWriter(client.getOutputStream(),true);
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		// Loop forever
		while(true) {
			// Pick a random number
			int quote = r.nextInt(quotes.size());
			// send the random quote
			out.println(quotes.get(quote));
			// Wait for a response. If none comes, the client is dead
			if(in.readLine() == null) {
				break;
			}
			// Sleep for five seconds
			try {
				Thread.sleep(5000);
			}catch(InterruptedException e) {}
		}
		// close in, out and the socket
		in.close();
		out.close();
		serverSocket.close();
	}
}