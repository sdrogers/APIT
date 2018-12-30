import java.util.ArrayList;
public class PhoneBook3 {

    // Inner class that holds one array entry...
    private class PhoneList {
        private ArrayList<String> names = new ArrayList<String>();
        private ArrayList<String> numbers = new ArrayList<String>();
        public void addEntry(String name,String number) {
            names.add(name);
            numbers.add(number);
        }
        public String getNumber(String name) {
            for(int i=0;i<names.size();i++) {
                if(names.get(i).equals(name)) {
                    return numbers.get(i);
                }
            }
            return "";
        }
    }
    private PhoneList[] mainList = new PhoneList[15];
    public PhoneBook3() {
        for(int i = 0;i<15;i++) {
            mainList[i] = new PhoneList();
        }
    }
    public void addEntry(String name, String number) {
        int l = name.length();
        mainList[l].addEntry(name, number);
    }
    public String getNumber(String name) {
        int l = name.length();
        return  mainList[l].getNumber(name);
    }

    public static void main(String[] args) {
        PhoneBook3 p = new PhoneBook3();
        p.addEntry("Simon", "0777777777");
        p.addEntry("Jennifer", "0666677889");
        p.addEntry("Ravi", "056782776");
        p.addEntry("Ken", "0447838827");
        p.addEntry("Hannah", "066848382");

        System.out.println(p.getNumber("Ravi"));
    }
}