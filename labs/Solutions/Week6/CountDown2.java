import javax.swing.*;
import java.awt.event.*;
import java.util.*;

// This is the solution to part 2.
// Value in textField is taken and passed to a 
// SwingWorker that then counts down from that value to zero
// pausing for a second at each step

// In addition is allows the counter to be stopped

public class CountDown2 extends JFrame implements ActionListener {


	// An internal Counter class...
	private class Counter extends SwingWorker<Void,Integer> {
		private Integer startVal;
		// As we need to set the textField in the CountDown2 object
		// we need a reference to the CountDown2 object
		private CountDown2 cd;
		public Counter(Integer startVal,CountDown2 cd) {
			this.cd = cd;
			this.startVal = startVal;
		}
		// The actual processing
		protected Void doInBackground() {
			for(int i=startVal;i>=0;i--) {
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					// e.printStackTrace();
					// When the worker is cancelled (stop button pressed)
					// we get here...
					return null;
				}
				publish(i); // publish each new value
			}
			return null;
		}
		// The method called by Java that we implement
		// to decide what to do when the display updates
		public void process(List<Integer> vals) {
			Integer finalVal = vals.get(vals.size()-1);
			cd.setCountField(""+finalVal);
		}
		public void done() {
			// Java automaticall calls this when we're finished
			// in this case, we should ask the CountDown2 object to reset its buttons
			this.cd.resetButtons();
		}
	}


	private JButton startButton,stopButton;
	private JTextField countField;
	private Counter counter; // We now need a ref so that we can cancel

	public CountDown2() {

		// Standard JFrame stuff...
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);

		JPanel contentPanel = new JPanel();
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		stopButton.setEnabled(false); // Stop is initially disabled
		countField = new JTextField("10",10);

		contentPanel.add(countField);
		contentPanel.add(startButton);
		contentPanel.add(stopButton);


		startButton.addActionListener(this);
		stopButton.addActionListener(this);

		this.add(contentPanel);

		this.setVisible(true);
	}
	// Method called by the worker
	public void setCountField(String newVal) {
		this.countField.setText(newVal);
	}

	// Called when the counter finishes by itself
	public void resetButtons() {
		startButton.setEnabled(true);
		stopButton.setEnabled(false);
		countField.setEditable(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			// user pressed start! Create a new counter and execute it
			Integer startValue = Integer.parseInt(countField.getText());
			this.counter = new Counter(startValue,this);
			this.counter.execute();
			countField.setEditable(false);
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
		}else if(e.getSource() == stopButton) {
			this.counter.cancel(true);
			countField.setEditable(true);
			startButton.setEnabled(true);
			stopButton.setEnabled(false);			
		}
	}


	public static void main(String[] args) {
		new CountDown2();
	}
}