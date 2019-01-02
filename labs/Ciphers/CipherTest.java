public class CipherTest {
    public static void main(String[] args) {
        String keyword = "RHINO";
        try {
            Mono m = new Mono(keyword);
            String message = "THIS IS A MESSAGE TO ENCODE";
            String output = "";
            for(int i=0;i<message.length();i++) {
                output += m.encode(message.charAt(i));
            }
            System.out.println(output); 
        }catch(BadKeywordException e) {
            e.printStackTrace();
        }
    }    
}