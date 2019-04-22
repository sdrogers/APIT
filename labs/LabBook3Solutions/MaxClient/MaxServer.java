import java.net.*;
import java.io.*;
import java.util.Random;

// Quite a lot going on here...
// The server has a couple of inner classes
// one that handles an individual client, sending it rows
// until there are no rows left
// and another that periodically checks if the 
// job is finished.
// The results array is initially populated with values of -1
// That way, once all values are not -1, we know the process has finished.


public class MaxServer {
    private static int nextRow = 0;
    private final int nRows = 2000;
    private final int nCols = 100;
    private Double[][] data = new Double[nRows][nCols];
    private Double[] rowMax = new Double[nRows];
    private class ClientThread extends Thread {
        Socket client;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        public ClientThread(Socket client) {
            this.client = client;
            try {
                oos = new ObjectOutputStream(this.client.getOutputStream());
                ois = new ObjectInputStream(this.client.getInputStream());
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        public void run() {
            while(true) {
                // Find a row that needs to be done
                int row = getNextRow();
                if(row < nRows) {
                    // Send the row to the client and await results
                    try {
                        System.out.println("Sending row " + row + " to client " + this.getName());
                        oos.writeObject(new IdxArray(row,data[row]));
                        MaxRes res = (MaxRes)(ois.readObject());
                        synchronized(rowMax) {
                            rowMax[res.getPos()] = res.getMax();
                        }
                    }catch(ClassNotFoundException e) {
                        e.printStackTrace();
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    break; // none left
                }
            }
        }
    }
    private class FinishChecker extends Thread {
        public void run() {
            // checks if all are done and if so, computes the max
            Double globalMax = 0.0;
            boolean finished = false;
            while(!finished) {
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(rowMax) {
                    globalMax = 0.0;
                    finished = true;
                    for(int i=0;i<nRows;i++) {
                        if(rowMax[i] < 0.0) {
                            finished = false;
                        }
                        if(rowMax[i] > globalMax) {
                            globalMax = rowMax[i];
                        }
                    }
                }
                if(!finished) {
                    System.out.println("Not finished yet");
                }
            }
            System.out.println("The max is: " + globalMax);
        }
    }
    // method to get the next row that needs to be processes
    // synchronised to avoid nasty races
    public int getNextRow() {
        synchronized(this) {
            int toReturn = nextRow;
            nextRow++;
            return toReturn;
        }
    }
    // Method that starts everything off
    public void runServer() {
        // create the random data
        Random r = new Random();
        for(int i=0;i<nRows;i++) {
            rowMax[i] = -1.0; // makes it easy to check if we've finished
            for(int j=0;j<nCols;j++) {
                data[i][j] = r.nextDouble();
            }
        }
        // Start the thread that checks if things have finished
        new FinishChecker().start();
        ServerSocket server = null;
        try {
            server = new ServerSocket(8765);
            while(true) {
                // Every time a new client arrives, start a new thread
                Socket client = server.accept();
                new ClientThread(client).start();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(server != null) {
                try {
                    server.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        new MaxServer().runServer();
    }    
}