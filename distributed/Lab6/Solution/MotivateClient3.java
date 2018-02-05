import java.net.*;
import java.io.*;

public class MotivateClient3 {
	private static String SERVER = "127.0.0.1";
	private static Integer PORT = 8765;
	public static void main(String[] args) throws IOException {
		// Connect to the server and create the writer and reader
		Socket socket = new Socket(SERVER,PORT);
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		// Loop forever
		while(true) {
			// Wait for the user to type a message
			String message = System.console().readLine();
			// Send this message to the server
			out.println(message);
			// Await the response
			message = in.readLine();
			// If its null, then the server has gone
			if(message == null) {
				break;
			}
			// Display the message
			System.out.println(message);
			// Send back a response
			out.println("RECEIVED");
		}
		// Close the in and out and socket
		out.close();
		in.close();
		socket.close();
	}
}