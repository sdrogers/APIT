import java.io.*;
import java.net.*;
public class SimpleClient2a {
	private static int PORT = 8765;
	// Note that this IP address is the local machine (always)
	private static String server = "127.0.0.1";
	public static void main(String[] args) throws IOException {
		// Make a socket and try and connect
		Socket socket = new Socket(server,PORT);
		PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
		System.out.println("Connnected - please type your message...");
		String input = System.console().readLine();
		writer.println(input);
		// Close the socket
		socket.close();
	}
	
}