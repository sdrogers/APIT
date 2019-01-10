import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;
public class Reader implements Runnable {
    private Socket socket;
    public Reader(Socket s) {
        this.socket = s;
    }
    public void run() {
        try {
            Scanner sc = new Scanner(this.socket.getInputStream());
            while(sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}