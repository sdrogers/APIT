//Client for the final part of the week 7 lab

import java.net.*;
import java.io.*;

public class QuoteClient3 {
	private static final int PORT = 8765;
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1",PORT);
			// We are connected...
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			String line = "";
			while(true) {
				// read a line from the console
				String input = System.console().readLine();
				// send to the server
				out.println(input);

				// display the result

				line = reader.readLine();
				System.out.println(line);	

				// while((line = reader.readLine())!=null) {
				// 	System.out.println(line);
				// }
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