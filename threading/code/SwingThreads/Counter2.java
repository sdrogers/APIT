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

	public Counter2() {
		super("Counter2"); // Run the super constructor (JFrame)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(3,10,3,10);
		countText = makeText();
		startButton = makeButton("StartUp");
		stopButton = makeButton("StopUp");
		resetButton = makeButton("Reset");

		pack();
		setVisible(true);
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Start") {
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			resetButton.setEnabled(false);
			(ct = new CountTask()).execute();
		}else if(e.getActionCommand() == "Stop") {
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			resetButton.setEnabled(true);
			ct.cancel(true);
			ct = null;
		}else if(e.getActionCommand() == "Reset") {
			countText.setText("0");
		}
	}


	private class CountTask extends SwingWorker<Void,Integer>{
		protected Void doInBackground() {
			try {
				Integer count = Integer.parseInt(countText.getText());
				while(!isCancelled()) {
					count++;
					Thread.sleep(100);
					publish(count);
					// countText.setText(String.format("%d",count)); - this would also work and should be ok, but is risky!
				}
				}catch(InterruptedException e) {

				}
			return null;
		}
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