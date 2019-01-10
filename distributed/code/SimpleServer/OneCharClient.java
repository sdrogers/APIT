import java.io.IOException;
import java.net.Socket;
import java.io.InputStreamReader;

public class OneCharClient {
    public static void main(String[] args) {
        Socket s = null;
        try {
            s = new Socket("127.0.0.1",8765); // IP Address and port
            // We are connected!
            InputStreamReader sr = new InputStreamReader(s.getInputStream());
            int c = sr.read();
            System.out.println((char)c); // Print the character
            sr.close(); // Close the reader
            s.close(); // close the socket
        }catch(IOException e) {
            e.printStackTrace();
        }
    }    
}