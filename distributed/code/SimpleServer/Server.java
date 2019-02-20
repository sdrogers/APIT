import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerSocket listener = null;
        Socket client = null;
        try {
            listener = new ServerSocket(8765);
            client = listener.accept(); // wait for a single client
            System.out.println("Client has arrived!");
            // At this point a client has arrived
            OutputStreamWriter os = new OutputStreamWriter(client.getOutputStream());
        
            Scanner textInput = new Scanner(System.in);
            while(true) {
                String line = textInput.nextLine();
                if(line.equals("END")) {
                    break;
                }
                os.write(line + '\n');
                os.flush();
            }
            textInput.close();
            client.close();
            listener.close();
        }catch(IOException e) {
            e.printStackTrace(); 
        }
    }    
}