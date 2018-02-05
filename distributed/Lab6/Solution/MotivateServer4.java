import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class MotivateServer4 {
	private static int PORT = 8765;
	private static Random r = new Random();
	private ArrayList<String> quotes;
	private ServerSocket serverSocket;
	private ArrayList<ClientThread> allClients = new ArrayList<ClientThread>();
	// I've moved this out of main for neatness
	public void runServer() {
		// Create the server socket
		try {
			serverSocket = new ServerSocket(PORT);
			// Load the motivational quotes
			ArrayList<String> quotes = QuoteLoader.loadQuotes("quotes.txt");
			// Wait for a connection from a client
			while(true) {
				Socket client = serverSocket.accept();
				ClientThread newClient = new ClientThread(client,quotes,allClients);
				allClients.add(newClient);
				newClient.start();

			}
		}catch(Exception e) {
		}finally {
			// close the socket
			try {
				serverSocket.close();
			}catch(Exception e) {
			}
		}
	}

	// The thread that will handle a client
	public static class ClientThread extends Thread {
		// Declare the various attributes of the thread
		private Socket client;
		private PrintWriter out;
		private BufferedReader in;
		private ArrayList<String> quotes;
		private ArrayList<ClientThread> allClients;
		public ClientThread(Socket socket,ArrayList<String> quotes,ArrayList<ClientThread> allClients) {
			client = socket;
			this.quotes = quotes;
			this.allClients = allClients;
		}
		// Override run...
		public void run() {
			try {
				// Create the writer and reader
				out = new PrintWriter(client.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String response;
				while(true) {
					// wait for a request from the client
					String message = in.readLine();
					if(message == null) {
						break;
					}else if(message.startsWith("MOTIVATEME")) {
						// response will be a random quote
						int quote = r.nextInt(quotes.size());
						response = quotes.get(quote);
					}else if(message.startsWith("ADD:")) {
						// Add the new quote - substring is used to chop off 'ADD:'
						synchronized(quotes) {
							quotes.add(message.substring(4));
						}
						response = "QUOTE ADDED";
					}else if(message.startsWith("USERS")){
						// Send back the number of users online
						response = "" + allClients.size() + " USERS ONLINE";
					}else if(message.startsWith("QUOTES")) {
						// Send back the number of quotes in the system
						response = "" + quotes.size() + " QUOTES IN THE SYSTEM";
					}else {
						// Unrecognized command!
						response = "SORRY, UNKNOWN COMMAND";
					}
					
					// send the response
					out.println(response);
					// Wait for a response. If none comes, the client is dead
					if(in.readLine() == null) {
						break;
					}
				}
			}catch(Exception e) {

			}finally {
				// Collapse nicely if the server goes
				try {
					in.close();
					out.close();
					client.close();
					// If this client is no longer connected, remove it from the list
					allClients.remove(this);
				}catch(Exception e) {}
			}
		}
	}


	public static void main(String[] args) {
		new MotivateServer4().runServer();
	}
}