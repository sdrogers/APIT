import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStreamReader;

public class Client {
    public static void main(String[] args) {
        Socket s = null;
        try {
            s = new Socket("127.0.0.1",8765); // IP Address and port
            // We are connected!
            Scanner sr = new Scanner(s.getInputStream());
            while(sr.hasNextLine()) {
                System.out.println(sr.nextLine());
            }
            sr.close(); // Close the reader
            s.close(); // close the socket
        }catch(IOException e) {
            e.printStackTrace();
        }
    }    
}