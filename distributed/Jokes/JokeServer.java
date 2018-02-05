import java.net.*;
import java.io.*;
public class JokeServer {
	private static int PORT = 8765;
	public static void main(String[] args) throws IOException {
		// Make a server object
		ServerSocket listener = new ServerSocket(PORT);
		// Wait for a connection and create a client
		Socket client = listener.accept();
		// Now we're connected, create a writer to send messages
		PrintWriter writer = new PrintWriter(client.getOutputStream(),true);
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

		writer.println("KNOCK KNOCK");
		String response = reader.readLine();
		System.out.println(response);
		if(response.equals("WHO'S THERE")) {
			writer.println("TISH");
			String response2 = reader.readLine();
			System.out.println(response2);
			if(response2.equals("TISH WHO?")) {
				writer.println("BLESS YOU!");
				reader.readLine();
			}else {
				writer.println("WRONG, IDIOT");
				client.close();
			}
		}else {
			writer.println("Don't you understand knock knock jokes??");
			client.close();
		}

		// Close the connection
		client.close();
	}
}