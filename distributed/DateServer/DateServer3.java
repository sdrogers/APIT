import java.io.*;
import java.net.*;
import java.util.Date;
public class DateServer3 {
	private static int PORT = 8765;
	private static ServerSocket listener;
	public static void main(String[] args)  {
		try {
			listener = new ServerSocket(PORT);
			while(true) {
				new Handler(listener.accept()).start();
			}
		}catch(IOException e) {
		}finally {
			try{
				listener.close();
			}catch(IOException e){}
		}
 	}

	public static class Handler extends Thread {
		private Socket socket;
		public Handler(Socket socket) {
			this.socket = socket;
		}
		public void run() {
			try {
				System.out.println("New connection started on thread " + this.getName());
				PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out.println("Hello - welcome to the date server. You're on thread " + this.getName());
				while(true) {
					String message = (new Date()).toString();
					out.println(message);
					if(in.readLine() == null) {
						return;
					}
					Thread.sleep(1000);
				}
			}catch(Exception e) {
			}finally {
				try {
					socket.close();
				}catch(IOException e) {
				}finally {
					System.out.println("\tConnection closed on thread " + this.getName());
				}
			}
		}
	}
}