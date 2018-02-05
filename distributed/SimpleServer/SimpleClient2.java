import java.io.*;
import java.net.*;
public class SimpleClient2 {
	private static int PORT = 8765;
	// Note that this IP address is the local machine (always)
	private static String server = "127.0.0.1";
	public static void main(String[] args) throws IOException {
		// Make a socket and try and connect
		Socket socket = new Socket(server,PORT);
		// Now we're connected, create a buffered reader object to read from the server
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		// Read a line (this waits until there is a line to read)
		String message = (String)reader.readLine();
		// Display the line
		System.out.println(message);
		// Close the socket
		socket.close();
	}
	
}