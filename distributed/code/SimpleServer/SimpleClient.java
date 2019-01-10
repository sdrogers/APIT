import java.io.*;
import java.net.*;

public class SimpleClient {
	// Change port and server to suit your needs
	private static int PORT = 8765;
	private static String server = "127.0.0.1";
	public static void main(String[] args) throws IOException {
		// Make a socket and try and connect
		Socket socket = new Socket(server,PORT);
		// We only reach this line once connected
		// Close the socket
		socket.close();
	}
	
}