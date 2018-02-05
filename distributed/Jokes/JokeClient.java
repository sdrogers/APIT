import java.io.*;
import java.net.*;
public class JokeClient {
	private static int PORT = 8765;
	// Note that this IP address is the local machine (always)
	private static String server = "127.0.0.1";
	public static void main(String[] args) throws IOException {
		// Make a socket and try and connect
		Socket socket = new Socket(server,PORT);
		// Now we're connected, create reader and writer objects to read from the server
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);

		String line = reader.readLine();
		System.out.println(line);
		if(line.equals("KNOCK KNOCK")) {
			writer.println("WHO'S THERE");
			String nextline = reader.readLine();
			System.out.println(nextline);
			writer.println(nextline + " WHO?");
			String punchline = reader.readLine();
			System.out.println(punchline);
		}else {
			writer.println("I DONT ETC");
			socket.close();
		}


		// Close the socket
		socket.close();
	}
	
}