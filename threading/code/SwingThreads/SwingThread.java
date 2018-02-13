import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import javax.swing.SwingUtilities;

// Our main class that extends JFrame and implements the 
// ActionListener interface
public class SwingThread extends JFrame implements ActionListener {
	// Create two button and two text field objects
	private final JButton startButton,stopButton;
	private final JTextField countField,outField;
	// Constructor: set the frame things, and make the buttons
	public SwingThread() {
		super("Swing Thread");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridBagLayout());

		startButton = makeButton("Start");
		stopButton = makeButton("Stop");

		countField = makeText();
		outField = makeText();
		pack();
		setVisible(true);
	}
	// The following two methods just make the 
	// text fields and buttons to avoid
	// repetition.
	private JTextField makeText() {
		JTextField b = new JTextField(5);
		getContentPane().add(b);
		return b;
	}
	private JButton makeButton(String caption) {
		JButton b = new JButton(caption);
		b.setActionCommand(caption);
		b.addActionListener(this);
		getContentPane().add(b);
		return b;
	}
	// Decide what to do if buttons are pressed
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Start") {
			outField.setText("Start");
			startCounting();
		}else if(e.getActionCommand() == "Stop") {
			outField.setText("Stop");
		}
	}
	// Do some SLOW counting
	private void startCounting() {
		try {
			for(int i=0;i<100;i++) {
				countField.setText(String.format("%d",i));
				Thread.sleep(100);
			}
		}catch(InterruptedException e) {

		}
	}
	// Main - you should always use invokeLater to do the 
	// creation of the SwingThread() object in the event
	// dispatch thread
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SwingThread();
			}	
		});
	}

}