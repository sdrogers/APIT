import java.io.Serializable;
public class Message implements Serializable {
    private String messageText;
    private String senderName;
    public Message(String messageText, String senderName) {
        this.messageText = messageText;
        this.senderName = senderName;
    }
    public String toString() {
        return this.senderName + ": " + this.messageText;
    }
}