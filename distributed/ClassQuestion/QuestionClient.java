import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class QuestionClient extends JFrame implements ActionListener {

	private static Socket socket;
	private static DefaultListModel<String> listModel;
	private static JList<String> list;
	private static JButton answerButton;
	private BufferedReader reader;
	private PrintWriter writer;
	private JLabel questionLabel;
	public QuestionClient() {
		super("Question Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		answerButton = makeButton("Send Answer");
		getContentPane().add(answerButton,BorderLayout.SOUTH);
		answerButton.setEnabled(false);
		questionLabel = new JLabel("Waiting for question");
		getContentPane().add(questionLabel,BorderLayout.NORTH);
		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		getContentPane().add(new JScrollPane(list),BorderLayout.CENTER);
		pack();
		setVisible(true);

		(new ClientWorker()).execute();
	}

	private JButton makeButton(String caption) {
		JButton b = new JButton(caption);
		b.setActionCommand(caption);
		b.addActionListener(this);
		return b;
	}
	public class ClientWorker extends SwingWorker<Void,Void> {
		public Void doInBackground() {
			try {
				socket = new Socket("127.0.0.1",8765);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(),true);
				while(true){
					String message = reader.readLine();
					if(message == null) {
						break;
					}else if (message.startsWith("QUESTION:")) {
						questionLabel.setText(message.substring(9));
						writer.println("RECEIVED");
					}else if (message.startsWith("ANSWER:")) {
						String answer = message.substring(7);
						listModel.addElement(answer);
						list.setSelectedIndex(0);
					    list.ensureIndexIsVisible(0);
						answerButton.setEnabled(true);
						writer.println("RECEIVED");
					}else if(message.startsWith("CLEARQUESTIONS")) {
						listModel.clear();
						writer.println("RECEIVED");
					}else if(message.startsWith("STOP")) {
						answerButton.setEnabled(false);
						writer.println("RECEIVED");
					}else {
						System.out.println(reader.readLine());
						writer.println("RECEIVED");
					}
				}
			}catch(Exception e) {
			}finally{
				try {
					socket.close();
				}catch(Exception e){}
				return null;
			}
		}
	}
	

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Send Answer") {
			String message = "ANSWER:"+list.getSelectedValue();
			writer.println(message);
			answerButton.setEnabled(false);
		}
	}

	public static void main(String[] args) {
		new QuestionClient();
	}

	
}