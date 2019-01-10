import java.io.*;
import java.net.*;
import java.util.Scanner;
public class SimpleClient2 {
	private static int PORT = 8765;
	// Note that this IP address is the local machine (always)
	private static String server = "127.0.0.1";
	public static void main(String[] args) throws IOException {
		// Make a socket and try and connect
		Socket socket = new Socket(server,PORT);
		// Now we're connected, create a buffered reader object to read from the server
		InputStreamReader reader = new InputStreamReader(socket.getInputStream());
		// Read a line (this waits until there is a line to read)
		while(true) {
			int a = reader.read();
			System.out.println(a);
			if(a==-1) {
				break;
			}
		}
		while(true) {
			int a = reader.read();
			System.out.println(a);
			if(a==-1) {
				break;
			}
		}
		
		// System.out.println(a);
		// Scanner s = new Scanner(reader);
		// String message = s.nextLine();
		// Display the line
		// System.out.println(message);
		// Close the socket
		socket.close();
	}
	
}