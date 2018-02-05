import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.HashMap;
public class QuestionServer extends JFrame implements ActionListener {
	private static ServerSocket listener;
	private JTextArea choiceArea;
	private JButton startButton;
	private JButton stopButton;
	private JTextField messageText;
	private JLabel messageLabel,instructions,topLabel;
	private JTextArea answerArea;
	private JTextField questionField;
	private JPanel buttonPanel,centerPanel,subPanel;
	private HashSet<PrintWriter> writers;
	private HashMap<String,Integer> answers;

	public QuestionServer() {
		// Lay everything out
		super("Class Questions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,2));
		startButton = makeButton("Send Questions",buttonPanel);
		stopButton = makeButton("Stop Results",buttonPanel);
		stopButton.setEnabled(false);
		questionField = new JTextField(30);
		messageLabel = new JLabel("Messages:");
		messageText = new JTextField(20);
		topLabel = new JLabel("0 Clients connected");
		getContentPane().add(topLabel,BorderLayout.NORTH);
		buttonPanel.add(messageLabel);
		buttonPanel.add(messageText);
		messageText.setEnabled(false);
		choiceArea = new JTextArea(10,30);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,2));
		instructions = new JLabel("Enter the question below and the answers to the right\n, separated by new line characters");
		subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(2,1));
		subPanel.add(instructions);
		subPanel.add(questionField);
		centerPanel.add(subPanel);
		centerPanel.add(choiceArea);
		getContentPane().add(centerPanel,BorderLayout.CENTER);
		getContentPane().add(buttonPanel,BorderLayout.SOUTH);
		answerArea = new JTextArea(10,30);
		pack();
		setVisible(true);
		writers = new HashSet<PrintWriter>();
		answers = new HashMap<String,Integer>();
		(new ServerListener()).execute();

	}
	// This is the swing worker for the client
	private class ClientRunner extends SwingWorker<Void,Void> {
		private Socket socket;
		PrintWriter out;
		BufferedReader in;
		public ClientRunner(Socket socket) throws Exception {
			this.socket = socket;
			out = new PrintWriter(this.socket.getOutputStream(),true);
			writers.add(out);
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			messageText.setText("Client connected (" + writers.size() + ")");
			topLabel.setText(writers.size() + " clients connected");
		}
		public Void doInBackground() {
			try {
				while(true) {
					// Receive the messages
					String message = in.readLine();
					if(message==null) {
						break;
					}else if (message.startsWith("ANSWER:")) {
						synchronized(answers) {
							String thisAnswer = message.substring(7);
							if(!answers.containsKey(thisAnswer)) {
								answers.put(thisAnswer,(Integer)1);
							}else{
								Integer temp = answers.get(thisAnswer);
								answers.put(thisAnswer,temp+1);
							}
							messageText.setText("Received an answer: " + thisAnswer);
						}
						updateAnswerArea();
					}
				}
			}catch(Exception e) {
				System.out.println(e);
			}finally{
				try{
					if(out!=null) {
						writers.remove(out);
					}
					socket.close();
					messageText.setText("Client disconnected (" + writers.size() + ")");
					topLabel.setText(writers.size() + " clients connected");
				}catch(Exception e){}
				return null;
			}
		}
	}
	private void updateAnswerArea() {
		choiceArea.setText("");
		synchronized(answers) {
			for(String key:answers.keySet()) {
				choiceArea.append(key + ":" + answers.get(key) + "\n");
			}
		}
	}
	// Sits and listens and creates clients as they arrive
	private class ServerListener extends SwingWorker<Void,Void> {
		public Void doInBackground() {
			try {
				listener = new ServerSocket(8765);
				while(true) {
					(new ClientRunner(listener.accept())).execute();
				}
			}catch(Exception e) {}
			return null;
		}
	}
	private JButton makeButton(String caption,JPanel thisPanel) {
		JButton b = new JButton(caption);
		b.setActionCommand(caption);
		thisPanel.add(b);
		b.addActionListener(this);
		return b;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Send Questions") {
			answerArea.setText("");
			synchronized(answers) {
				answers.clear();
			}
			for(PrintWriter writer:writers) {
				writer.println("CLEARQUESTIONS");
			}
			String question = questionField.getText();
			for(PrintWriter writer: writers) {
				String message = "QUESTION:" + question;
				writer.println(message);
			}
			String[] allAnswers = choiceArea.getText().split("\n");
			for(String answer: allAnswers) {
				String message = "ANSWER:" + answer;
				answers.put(answer,(Integer)0);
				for(PrintWriter writer: writers) {
					writer.println(message);
				}				
			}
			updateAnswerArea();
			choiceArea.setEnabled(false);
			stopButton.setEnabled(true);
		}
		if(e.getActionCommand() == "Stop Results") {
			stopButton.setEnabled(false);
			startButton.setEnabled(true);
			choiceArea.setEnabled(true);
			for(PrintWriter writer:writers) {
				writer.println("STOP");
			}
		}
	}
	public static void main(String[] args) {
		new QuestionServer();
	}
}