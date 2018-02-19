import javax.swing.*;
import java.awt.event.*;
import java.util.*;

// This is the solution to part 3.
// Value in textField is taken and passed to a 
// SwingWorker that then counts down from that value to zero
// pausing for a second at each step

// In addition is allows the counter to be stopped

public class CountDown3 extends JFrame {


	// An internal class that extends JPanel and includes all counting things
	private class CountPanel extends JPanel implements ActionListener {
		// Most of the stuff that was in CountDown2 is now in this inner class


		// An internal Counter class...
		private class Counter extends SwingWorker<Void,Integer> {
			private Integer startVal;
			private CountPanel cp; // Parent is now a panel
			public Counter(Integer startVal,CountPanel cp) {
				this.cp = cp;
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
				cp.setCountField(""+finalVal);
			}
			public void done() {
				// Java automaticall calls this when we're finished
				// in this case, we should ask the CountDown3 object to reset its buttons
				this.cp.resetButtons();
			}
		}

		private JButton startButton,stopButton;
		private JTextField countField;
		private Counter counter; // We now need a ref so that we can cancel

		public CountPanel() {
			// The panel now owns the buttons
			startButton = new JButton("Start");
			stopButton = new JButton("Stop");
			stopButton.setEnabled(false); // Stop is initially disabled
			countField = new JTextField("10",10);

			// the panel now handles the listening
			startButton.addActionListener(this);
			stopButton.addActionListener(this);

			// add the components to the panel
			this.add(countField);
			this.add(startButton);
			this.add(stopButton);


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
	}

	
	public CountDown3() {

		// Standard JFrame stuff...
		// Not much in this class any more...
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);

		// Create a panel to put the CountPanels in..
		JPanel contentPanel = new JPanel();

		// Let's make 3 countpanels...
		contentPanel.add(new CountPanel());
		contentPanel.add(new CountPanel());
		contentPanel.add(new CountPanel());

		this.add(contentPanel);
		this.setVisible(true);

	}
	


	public static void main(String[] args) {
		new CountDown3();
	}
}