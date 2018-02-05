import java.net.*;
import java.io.*;
public class DateClient {
	private static String server = "127.0.0.1";
	private static int PORT = 8765;
	private static Socket socket;
	public static void main(String[] args) throws IOException {
		try{
			socket = new Socket(server,PORT);
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
			while(true) {
				System.out.println(reader.readLine());
			}
		}finally {
			socket.close();
		}
	}
}