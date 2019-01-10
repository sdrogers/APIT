import java.io.*;
import java.net.*;
import java.util.Date;
public class DateServer2 {
	private static int PORT = 8765;
	private static ServerSocket listener;
	public static void main(String[] args)  {
		try {
			listener = new ServerSocket(PORT);
			while(true) {
				// Socket a = listener.accept();
				// Handler h = new Handler(a);
				// h.start();
				new Handler(listener.accept()).start();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try{
				listener.close();
			}catch(IOException e){
				e.printStackTrace();
			}
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
				out.println("Hello - welcome to the date server. You're on thread " + this.getName());
				while(true) {
					String message = (new Date()).toString();
					out.println(message);
					Thread.sleep(500);
				}
			}catch(InterruptedException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					this.socket.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}