import java.net.*;
import java.io.*;
public class SimpleServer2 {
	private static int PORT = 8765;
	public static void main(String[] args) throws IOException {
		// Make a server object
		ServerSocket listener = new ServerSocket(PORT);
		// Wait for a connection and create a client
		while(true) {
			Socket client = listener.accept();
			// Now we're connected, create a writer to send messages
			PrintWriter writer = new PrintWriter(client.getOutputStream(),true);
			// send a message
			writer.println("Hello");
			writer.println("Boo");
			try {
			while(true) {

			}
		}finally{
			// Close the connection
			client.close();
		}
		}
	}
}