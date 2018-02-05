import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.HashMap;

public class ConsoleChat {
	private static int PORT = 8765;
	private static HashSet<String> names = new HashSet<String>();
	private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	private static HashMap<String,PrintWriter> name_writers = new HashMap<String,PrintWriter>();
	public static void main(String[] args) throws Exception {
		System.out.println("Started chat server - waiting for connections");
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
					if(name == null) {
						return;
					}
					synchronized(names) {
						if(!names.contains(name)) {
							names.add(name);
							name_writers.put(name,out);
							for(String temp_name : name_writers.keySet()) {
								PrintWriter writer = name_writers.get(temp_name);
								writer.println("MESSAGE:" + name + " has joined");
							}
							break;
						}
					}
				}
				out.println("NAMEACCEPTED");

				String current = "CURRENT:";
				for(String name : names ) {
					current += name + ",";
				}
				out.println(current);

				writers.add(out);

				while(true) {
					String line = in.readLine();
					if(line == null) {
						break;
					}
					if(line.startsWith("WHO")) {
						current = "CURRENT:";
						for(String name : names) {
							current += name + ",";
						}
						out.println(current);
					}else if (line.startsWith("PRIVATE")) {
						String[] split = line.split(":");
						String toName = split[1];
						PrintWriter writer = name_writers.get(toName);
						if(writer==null) {
							out.println("ERROR:User not online");
						}else {
							String message = "MESSAGE:" + name + "(private): " + split[2];
							writer.println(message);
						}

					}else{
						String message = "MESSAGE:" + name + ": " + line;
						for(String temp_name : name_writers.keySet()) {
							PrintWriter writer = name_writers.get(temp_name);
							writer.println(message);
						}
					}
				}
			}catch(IOException e) {
				System.out.println(e);
			}finally {
				if(name!=null) {
					synchronized(names) {
						names.remove(name);
						name_writers.remove(name);
					}
				}
				if(out!=null) {
					synchronized(writers) {
						writers.remove(out);
					}
				}
				try {
					socket.close();
				}catch (IOException e) {
				}
				for(PrintWriter writer: writers) {
					writer.println("MESSAGE:" + name + " has left");
				}
			}
		}
	}
}