import java.net.Socket;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
public class Writer implements Runnable {
    private Socket socket;
    public Writer(Socket s) {
        this.socket = s;
    }
    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            OutputStreamWriter os = new OutputStreamWriter(socket.getOutputStream());
            String line;
            while(!(line = sc.nextLine()).equals("END")) {
                os.write(line + '\n');
                os.flush();
            }
            sc.close();
            os.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}