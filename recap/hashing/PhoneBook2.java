public class PhoneBook2 {
    private String[] names = new String[15];
    private String[] numbers = new String[15];
    public void addEntry(String name,String number) {
        int l = name.length();
        names[l] = name;
        numbers[l] = number;
    }
    public String getNumber(String name) {
        int l = name.length();
        return numbers[l];
    }

    public static void main(String[] args) {
        PhoneBook2 p = new PhoneBook2();
        p.addEntry("Simon", "0777777777");
        p.addEntry("Jennifer", "0666677889");
        p.addEntry("Ravi", "056782776");
        p.addEntry("Ken", "0447838827");
        p.addEntry("Hannah", "066848382");

        System.out.println(p.getNumber("Ravi"));
    }
}