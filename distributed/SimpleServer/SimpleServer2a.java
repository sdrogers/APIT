import java.net.*;
import java.io.*;
public class SimpleServer2a {
	private static int PORT = 8765;
	public static void main(String[] args) throws IOException {
		// Make a server object
		ServerSocket listener = new ServerSocket(PORT);
		// Wait for a connection and create a client
		Socket client = listener.accept();
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String message = (String)reader.readLine();
		System.out.println("Message = " + message);
		// Close the connection
		client.close();
	}
}