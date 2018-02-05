import java.net.*;
import java.io.*;
public class DateClient3 {
	private static String server = "127.0.0.1";
	private static int PORT = 8765;
	public static void main(String[] args) throws IOException{
		//Create the socket, a reader and a writer
		Socket socket = new Socket(server,PORT);
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		/* Note that the writer helps the server 
		  know if this client has gone */
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		// Loop forever
		while(true){
			// Wait for something in reader
			String a = reader.readLine();
			// If we get null, the server has gone
			if(a==null) {
				socket.close();
				return;
			}else {
				// Otherwise display what we got
				System.out.println(a);
				// Reply so the server knows we're awake
				out.println("RECEIVED");
			}
		}
	}
}