import java.util.ArrayList;
import java.util.Iterator;
public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<String>();
        al.add("Simon");
        al.add("Ella");
        Iterator i = al.iterator();
        while(i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
