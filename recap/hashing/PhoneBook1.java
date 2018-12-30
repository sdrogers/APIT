import java.util.ArrayList;
public class PhoneBook1 {
    private ArrayList<String> names;
    private ArrayList<String> numbers;
    public PhoneBook1() {
        names = new ArrayList<String>();
        numbers = new ArrayList<String>();
    }    
    public void addEntry(String name, String number) {
        names.add(name);
        numbers.add(number);
    }
    public String getNumber(String name) {
        for(int i=0;i<names.size();i++) {
            if(names.get(i).equals(name)) {
                return numbers.get(i);
            }
        }
        return ""; // If name doesn't exist
    }

    public static void main(String[] args) {
        PhoneBook1 p = new PhoneBook1();
        p.addEntry("Simon", "0777777777");
        p.addEntry("Jennifer", "0666677889");
        p.addEntry("Ravi", "056782776");
        p.addEntry("Ken", "0447838827");
        p.addEntry("Hannah", "066848382");

        System.out.println(p.getNumber("Ravi"));
    }
}