import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.SwingUtilities;

// This is the thread safe version of our counter
// The difference lies in the fact that the counting
// is now done by an object that extends SwingWorker

public class Counter extends JFrame implements ActionListener {
	private final GridBagConstraints constraints;
	private final JButton startButton, stopButton, resetButton;
	private final JTextField countText;
	private CountTask ct;
	private JButton makeButton(String caption) {
		JButton b = new JButton(caption);
		b.setActionCommand(caption);
		b.addActionListener(this);
		getContentPane().add(b,constraints);
		return b;
	}
	private JTextField makeText() {
		JTextField t = new JTextField(20);
		getContentPane().add(t,constraints);
		t.setText("0");
		return t;
	}

	public Counter() {
		super("Counter"); // Run the super constructor (JFrame)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(3,10,3,10);
		countText = makeText();
		startButton = makeButton("Start");
		stopButton = makeButton("Stop");
		resetButton = makeButton("Reset");

		pack();
		setVisible(true);
	}


	public void actionPerformed(ActionEvent e) {
		// Note the enabled status of buttons is 
		// changed depending on which state we're in
		if(e.getActionCommand() == "Start") {
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			resetButton.setEnabled(false);
			// Create a new CountTask object and call its execute method
			// This is the equivalent of Thread.start()
			(ct = new CountTask()).execute();
		}else if(e.getActionCommand() == "Stop") {
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			resetButton.setEnabled(true);
			// SwingWorker.cancel(true) stops it
			ct.cancel(true);

			ct = null;
		}else if(e.getActionCommand() == "Reset") {
			countText.setText("0");
		}
	}

	// This is the object that does the counting
	// The two arguments in the <> are the return type
	// of doInBackground() and the type that you want to pass
	// to publish()
	private class CountTask extends SwingWorker<Void,Integer>{
		protected Void doInBackground() {
			try {
					Integer count = Integer.parseInt(countText.getText());
					while(!isCancelled()) {
						count++;
						Thread.sleep(100);
						// Publish is a method provided by SwingWorker that stores
						// the count variables in a list that can be accessed by process
						publish(count); 
					}
				}catch(InterruptedException e) {}
			return null;
		}
		// Every now and then the event dispatch thread will call process
		// In this example, I get it to set the count text value
		protected void process(List<Integer> counts) {
			int lastVal = counts.get(counts.size()-1);
			countText.setText(String.format("%d",lastVal));
		}
	}


	public static void main(String[] args) {	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Counter();
			}
		});
	}

}