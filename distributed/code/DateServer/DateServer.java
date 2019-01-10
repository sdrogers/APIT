import java.io.*;
import java.net.*;
import java.util.Date;
public class DateServer {
	private static int PORT = 8765;
	private static Socket client;
	private static ServerSocket listener;
	public static void main(String[] args) {
		try {
			listener = new ServerSocket(PORT);
			client = listener.accept();
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			while(true) {
				out.println((new Date()).toString());
				Thread.sleep(500);
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				client.close();
				listener.close();
			}catch(IOException e) {}
		}
	}
}