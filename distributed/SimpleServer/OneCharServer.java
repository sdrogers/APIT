import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;
import java.io.IOException;
import java.io.OutputStreamWriter;
public class OneCharServer {
    public static void main(String[] args) {
        ServerSocket listener = null;
        Socket client = null;
        try {
            listener = new ServerSocket(8765);
            client = listener.accept(); // wait for a single client
            System.out.println("Client has arrived!");
            // At this point a client has arrived

            OutputStreamWriter os = new OutputStreamWriter(client.getOutputStream());
        
            os.write('x');
            os.flush();

            client.close();
            listener.close();
        }catch(IOException e) {
            e.printStackTrace(); 
        }
    }    
}