import javax.swing.*;
import java.awt.event.*;
import java.util.*;

// This is the solution to part 1.
// Value in textField is taken and passed to a 
// SwingWorker that then counts down from that value to zero
// pausing for a second at each step

public class CountDown extends JFrame implements ActionListener {


	// An internal Counter class...
	private class Counter extends SwingWorker<Void,Integer> {
		private Integer startVal;
		// As we need to set the textField in the CountDown object
		// we need a reference to the CountDown object
		private CountDown cd;
		public Counter(Integer startVal,CountDown cd) {
			this.cd = cd;
			this.startVal = startVal;
		}
		// The actual processing
		protected Void doInBackground() {
			for(int i=startVal;i>=0;i--) {
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
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
	}


	private JButton startButton;
	private JTextField countField;

	public CountDown() {

		// Standard JFrame stuff...
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);

		JPanel contentPanel = new JPanel();
		startButton = new JButton("Start");
		countField = new JTextField("10",10);

		contentPanel.add(countField);
		contentPanel.add(startButton);

		startButton.addActionListener(this);

		this.add(contentPanel);

		this.setVisible(true);
	}
	// Method called by the worker
	public void setCountField(String newVal) {
		this.countField.setText(newVal);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			// user pressed start! Create a new counter and execute it
			Integer startValue = Integer.parseInt(countField.getText());
			new Counter(startValue,this).execute();

		}
	}


	public static void main(String[] args) {
		new CountDown();
	}
}