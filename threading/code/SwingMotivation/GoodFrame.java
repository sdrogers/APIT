import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GoodFrame extends JFrame implements ActionListener {

	private JTextField numberField;
	private JButton startButton;


	// This is the correct way to make things threaded in Swing
	// A SwingWorker object is used
	// and the 'worker' code is but in the doInBackground method
	// we can worry more about the details later...
	public class CounterWorker extends SwingWorker<Void,Integer> {
		private Integer startVal;
		public JTextField outputField;
		public CounterWorker(Integer s,JTextField tf) {
			this.startVal = s;
			this.outputField = tf;
		}
		public Void doInBackground() {
			for(Integer i=this.startVal;i>=0;i--) {
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				publish(i);
			}
			return null;
		}
		protected void process(List<Integer> vals) {
			int len = vals.size();
			this.outputField.setText("" + vals.get(len-1));
		}
	}


	public GoodFrame() {
		this.setSize(500,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		numberField = new JTextField("5",20);
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		
		JPanel contentPanel = new JPanel();
		contentPanel.add(numberField);
		contentPanel.add(startButton);
		this.add(contentPanel);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		int startVal = Integer.parseInt(this.numberField.getText());
		// Create the new worker object and execute it
		new CounterWorker(startVal,this.numberField).execute();
	}


	public static void main(String[] args) {
		new GoodFrame();
	}
}