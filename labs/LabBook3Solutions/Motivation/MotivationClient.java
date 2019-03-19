import java.net.*;
import java.io.*;
public class MotivationClient {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("127.0.0.1",8765);

		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		while(true) {
			System.out.println(reader.readLine());
		}
	}
}