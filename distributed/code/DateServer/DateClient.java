import java.net.*;
import java.io.*;
import java.util.Scanner;
public class DateClient {
	private static String server = "127.0.0.1";
	private static int PORT = 8765;
	private static Socket socket;
	public static void main(String[] args) {
		try{
			socket = new Socket(server,PORT);
			Scanner reader = new Scanner(socket.getInputStream());
			while(reader.hasNextLine()) {
				System.out.println(reader.nextLine());
			}
			reader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				socket.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}