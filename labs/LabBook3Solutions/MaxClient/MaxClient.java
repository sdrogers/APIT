import java.net.*;
import java.io.*;
// class for a client
// everything in main
// repeats the following:
// - receives a row and position
// - finds the max
// - returns the max and the position
public class MaxClient {
    public static void main(String[] args) {
        Socket connection = null;
        try {
            connection = new Socket("127.0.0.1",8765);
            ObjectInputStream ios = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
            while(true) {
                    Object o = ios.readObject();
                    IdxArray posAndArray;
                    posAndArray = (IdxArray)o;
                    int pos = posAndArray.getPos();
                    System.out.println("Received row " + pos);
                    Double[] a = posAndArray.getA();
                    Double max = -1.0;
                    for(int i=0;i<a.length;i++) {
                        if(a[i] > max) {
                            max = a[i];
                        }
                    }
                    oos.writeObject(new MaxRes(pos,max));
            }
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch(EOFException e) {
            System.exit(0);        
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(connection != null) {
                try {
                    connection.close();
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }    
}