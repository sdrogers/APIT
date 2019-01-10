import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer implements Runnable{
    private class ClientRunner implements Runnable {
        private Socket s = null;
        private ChatServer parent = null;
        private ObjectInputStream inputStream = null;
        private ObjectOutputStream outputStream = null;
        public ClientRunner(Socket s,ChatServer parent) {
            this.s = s;
            this.parent = parent;
            try {
                outputStream = new ObjectOutputStream(this.s.getOutputStream());
                inputStream = new ObjectInputStream(this.s.getInputStream());
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        public void run() {
            // receive messages
            try {
                Message message = null;
                while((message = (Message)inputStream.readObject())!= null) {
                    this.parent.transmit(message);
                }
                inputStream.close();
            }catch(ClassNotFoundException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        public void transmitMessage(Message m) {
            try {
                outputStream.writeObject(m);
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    private ServerSocket server;
    private ArrayList<ClientRunner> clients = new ArrayList<ClientRunner>();
    public ChatServer() {
        try {
            server = new ServerSocket(8765);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        while(true) {
            Socket clientSocket = null;
            try {
                clientSocket = server.accept();
                System.out.println("New client connected");
                ClientRunner client = new ClientRunner(clientSocket,this);
                clients.add(client);
                new Thread(client).start();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void transmit(Message m) {
        for(ClientRunner c: clients) {
            if(c != null) {
                c.transmitMessage(m);
            }
        }
    }
    public static void main(String[] args) {
        Thread t = new Thread(new ChatServer());
        t.start();
        try {
            t.join();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}