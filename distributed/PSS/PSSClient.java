import java.io.*;
import java.net.*;

public class PSSClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1",1234);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			String line = in.readLine(); // Wait for hello from server
			String[] tokens = line.split(":");
			int id = Integer.parseInt(tokens[1]);
			System.out.println("You are player " + id);
			line = in.readLine();
			System.out.println(line);
			while(true) { // loop forever
				line = in.readLine();
				System.out.println(line);
				// get the choice from the console and send
				line  = System.console().readLine();
				out.println(""+id+":"+line);
				line = in.readLine();
				System.out.println(line);
				System.out.println(in.readLine());
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}