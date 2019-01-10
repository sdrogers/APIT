import java.net.Socket;
import java.io.IOException;
public class WalkyTalkyClient {
    public static void main(String[] args) {
        Socket server = null;
        try {
            server = new Socket("127.0.0.1",8765);
            Thread readThread = new Thread(new Reader(server));
            Thread writeThread = new Thread(new Writer(server));
            readThread.start();
            writeThread.start();
            readThread.join();
            writeThread.join();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                server.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}