import java.net.*;
import java.io.*;
import java.util.*;
public class MotivationClient2 {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("127.0.0.1",8765);

		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
		Scanner userEntry = new Scanner(System.in);
		while(true) {
			String line = userEntry.nextLine();
			writer.println(line);
			System.out.println(reader.readLine());
		}
	}
}