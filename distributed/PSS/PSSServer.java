import java.io.*;
import java.net.*;

public class PSSServer {
	public final int PORT = 1234;
	private ServerSocket listener;
	private Player[] players;

	public static class Player {
		private BufferedReader in;
		private PrintWriter out; 
		private Socket socket;
		private int id;
		public Player(Socket s,int id) {
			this.socket = s;
			this.id = id;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(),true);
			}catch(IOException e) {

			}
		}
		public void welcome() {
			out.println("2 players have joined. Welcome to the game");
		}
		public void send(String message) {
			out.println(message);
		}
		public String read() throws IOException {
			return in.readLine();
		}
	}

	public PSSServer() {
		players = new Player[2];
	}
	public void start() {
		
		int playerPos = 0;
		try {
			listener = new ServerSocket(PORT);
			while(playerPos < 2) {
				Socket newSocket = listener.accept();
				Player player = new Player(newSocket,playerPos);
				players[playerPos] = player;
				System.out.println("Accepted player " + (playerPos));
				player.send("ID:" + playerPos);
				playerPos ++;
			}

			for(Player player: players) {
				player.welcome();
			}

			int round = 0;
			int[] playerWins = {0,0};
			while(true) {
				String message = "Round " + round + " send you choice...";
				for(Player player: players) {
					player.send(message);
				}
				String choice[] = new String[2];
				for(int i=0;i<2;i++) {
					String line = players[i].read();
					System.out.println(line);
					String[] tokens = line.split(":");
					int id = Integer.parseInt(tokens[0]);
					choice[id] = tokens[1].toLowerCase();
				}
				message = "Player 0: " + choice[0] + " player 1: "+choice[1];
				System.out.println(message);
				for(Player player: players) {
					player.send(message);
				}
				// Decide who wins
				int result = -99;
				if(choice[0].equals("paper")) {
					if(choice[1].equals("paper")) {
						result = 0;
					}else if(choice[1].equals("scissors")) {
						result = -1;
					}else if(choice[1].equals("stone")) {
						result = 1;
					}
				}else if(choice[0].equals("scissors")) {
					if(choice[1].equals("paper")) {
						result = 1;
					}else if(choice[1].equals("scissors")) {
						result = 0;
					}else if(choice[1].equals("stone")) {
						result = -1;
					}
				}else if(choice[0].equals("stone")) {
					if(choice[1].equals("paper")) {
						result = -1;
					}else if(choice[1].equals("scissors")) {
						result = 1;
					}else if(choice[1].equals("stone")) {
						result = 0;
					}					
				}
				String playerOneMessage = "";
				String playerTwoMessage = "";
				if(result == 1) {
					playerWins[0] += 1;
					playerOneMessage += "You won!";
					playerTwoMessage += "You lost!";
				}else if(result == -1) {
					playerWins[1] += 1;
					playerOneMessage += "You lost!";
					playerTwoMessage += "You won!";
				}else if(result == 0) {
					playerOneMessage += "You drew!";
					playerTwoMessage += "You drew!";					
				}else {
					playerOneMessage += "One of you idiots didn't enter something correct";
					playerTwoMessage += "One of you idiots didn't enter something correct";
				}
				playerOneMessage += " Scores: You: " + playerWins[0] + " Them: " + playerWins[1];
				playerTwoMessage += " Scores: You: " + playerWins[1] + " Them: " + playerWins[0];

				players[0].send(playerOneMessage);
				players[1].send(playerTwoMessage);

				round++;

				System.out.println("After " + round + " rounds, scores are: Player 0: " + playerWins[0] + " Player 1: " + playerWins[1]);

				
			}

		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				listener.close();			
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}	
	}

	public static void main(String[] args) {
		new PSSServer().start();
	}
}