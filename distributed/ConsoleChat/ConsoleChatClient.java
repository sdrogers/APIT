import java.io.*;
import java.net.*;

public class ConsoleChatClient {

	public static class writeThread extends Thread {
		Socket socket;
		PrintWriter out;
		public writeThread(Socket socket,PrintWriter out) {
			this.socket = socket;
			this.out = out;
		}
		public void run() {			
			while(true) {
				String line = System.console().readLine();
				out.println(line);
			}
		}

	}

	public static class readThread extends Thread {
		Socket socket;
		PrintWriter out;
		BufferedReader in;
		public readThread(Socket socket,PrintWriter out,BufferedReader in) {
			this.socket = socket;
			this.out = out;
			this.in = in;
		}
		public void run() {
			try{
				while(true) {
					String line = in.readLine();
					if(line.startsWith("MESSAGE:")) {
						System.out.println(line.substring(8));
					}else if(line.startsWith("CURRENT:")) {
						System.out.print("Currently online: ");
						System.out.println(line.substring(8));
					}else if(line.startsWith("ERROR:")) {
						System.out.println(line);
					}
				}
			}catch(IOException e){}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Please enter IP address:");
		String address = System.console().readLine();
		System.out.println("Please enter PORT:");
		Integer port = Integer.parseInt(System.console().readLine());
		Socket socket = new Socket(address,port);


		// Initial negotiation
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		
			while(true) {
				String line = in.readLine();
				if(line.startsWith("SUBMITNAME")) {
					System.out.println("Enter screen name: ");
					String name = System.console().readLine();
					out.println(name);
				}else if(line.startsWith("NAMEACCEPTED")) {
					break;
				}
			}
		

			readThread rt = new readThread(socket,out,in);
			rt.start();
			writeThread wt = new writeThread(socket,out);
			wt.start();
		}catch(IOException e) {}
		
	}
}