import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class MessageServer {
    public static void main(String[] args) {
        ServerSocket s = null;
        Socket client = null;
        try {
            s = new ServerSocket(8765);
            client = s.accept();
            Thread readThread = new Thread(new Reader(client));
            Thread writeThread = new Thread(new Writer(client));
            readThread.start();
            writeThread.start();
            try {
                readThread.join();
                writeThread.join();
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
                s.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}