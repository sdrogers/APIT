import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
public class ChatClient extends JFrame implements ActionListener {
	private static final int PORT = 8765;
	private BufferedReader in;
	private PrintWriter out;

	private JTextField textField;
	private JTextArea messageArea;

	public ChatClient() {
		super("Chat Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textField = new JTextField(40);
		messageArea = new JTextArea(8,40);
		textField.setEditable(false);
		textField.addActionListener(this);
		messageArea.setEditable(false);


		// This makes the messageArea autoscroll to the last message
		DefaultCaret caret = (DefaultCaret)messageArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);


		getContentPane().add(textField,"North");
		getContentPane().add(new JScrollPane(messageArea),"Center");

		pack();
		setVisible(true);
	}

	private String getServerAddress() {
		return JOptionPane.showInputDialog(this,"Enter IP Address of the Server:",
			"Welcome to the chat app",
			JOptionPane.QUESTION_MESSAGE);
	}
	private String getScreenName() {
		return JOptionPane.showInputDialog(this,"Enter a sceeen name:",
			"Screen name selection",
			JOptionPane.PLAIN_MESSAGE);
	}
	public void actionPerformed(ActionEvent e) {
		out.println(textField.getText());
		textField.setText("");
	}
	private void run() throws IOException {
		String serverAddress = getServerAddress();
		Socket socket = new Socket(serverAddress,PORT);

		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(),true);
		while(true) {
			String line = in.readLine();
			if(line.startsWith("SUBMITNAME")) {
				out.println(getScreenName());
			} else if (line.startsWith("NAMEACCEPTED")) {
				textField.setEditable(true);
			} else if (line.startsWith("MESSAGE")) {
				messageArea.append(line.substring(7) + "\n");
			}
		}
	}


	public static void main(String[] args) throws Exception {
		ChatClient c = new ChatClient();
		c.run();
	}
}