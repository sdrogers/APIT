import java.io.*;
import java.net.*;
import java.util.HashSet;

public class ChatServer {
	private static final int PORT = 8765;
	private static HashSet<String> names = new HashSet<String>();
	private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

	public static void main(String[] args) throws Exception {
		System.out.println("Running Chat Server");
		ServerSocket listener = new ServerSocket(PORT);
		try {
			while(true) {
				new Handler(listener.accept()).start();
			}
		}finally {
			listener.close();
		}
	}

	public static class Handler extends Thread {
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(),true);

				while(true) {
					out.println("SUBMITNAME");
					name = in.readLine();
					if (name == null) {
						return;
					}
					synchronized(names) {
						if(!names.contains(name)) {
							names.add(name);
							for(PrintWriter writer : writers) {
								writer.println("MESSAGE"+name+" has joined");
							}
							break;
						}
					}
				}


				out.println("NAMEACCEPTED");
				writers.add(out);

				while(true) {
					String message = in.readLine();
					if(message == null) {
						break;
					}
					message = "MESSAGE" + name + ": " + message;
					for(PrintWriter writer : writers) {
						writer.println(message);
					}
					
				}

			}catch(IOException e) {
				System.out.println(e);
			}finally {
				if (name!=null) {
					synchronized(names) {
						names.remove(name);
					}
				}
				if (out!=null) {
					synchronized(writers){
						writers.remove(out);
					}
				}
				try {
					socket.close();
				}catch (IOException e) {
				}
				for(PrintWriter writer: writers) {
					writer.println("MESSAGE" + name + " has left");
				}
			}
		}
	}
}