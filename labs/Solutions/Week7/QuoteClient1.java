import java.net.*;
import java.io.*;

public class QuoteClient1 {
	private static final int PORT = 8765;
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1",PORT);
			// We are connected...
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = "";
			// The following is a neat way of assigning a value to 
			// line and checking if it is null or not
			// note that readLine() returning null is an indication
			// that the connection has been lost
			// i.e. we're looping until the connection has gone
			while((line = reader.readLine())!=null) {
				System.out.println(line);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			try {
				// It's good practice to close the socket
				// in a finally.
				// But...you'll need to catch possible
				// exceptions here too...
				if(socket != null) {
					socket.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}