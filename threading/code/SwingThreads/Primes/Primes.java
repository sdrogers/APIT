// Computes the primes in an inefficient way

import java.awt.event.*;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.util.List;


public class Primes extends JFrame implements ActionListener {
	private JButton startButton,stopButton,resetButton;
	private JTextField searchPoint,noFound;
	private JTextArea foundPrimes;
	private JScrollPane scrollPane;
	private PrimeFinder pf;
	private int nFound = 0;
	public Primes() {
		super("Prime finder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());

		startButton = makeButton("Start");
		stopButton = makeButton("Stop");
		stopButton.setEnabled(false);
		resetButton = makeButton("Reset");
		resetButton.setEnabled(false);

		// This will display the number of primes I've found
		noFound = new JTextField(10);
		noFound.setText("0");
		noFound.setEnabled(false);
		getContentPane().add(noFound);

		// This will display which integer I'm currently testing 
		searchPoint = new JTextField(10);
		searchPoint.setText("0");
		searchPoint.setEnabled(false);
		getContentPane().add(searchPoint);

		// A scrollable text area to show all the ones found so far
		foundPrimes = new JTextArea(20,10);
		foundPrimes.setEnabled(false);
		scrollPane = new JScrollPane(foundPrimes);
		getContentPane().add(scrollPane);

		pack();
		setVisible(true);
	}


	private JButton makeButton(String caption) {
		JButton b = new JButton(caption);
		b.setActionCommand(caption);
		b.addActionListener(this);
		getContentPane().add(b);
		return b;
	}

	// Manage the clicks - enabling and unenabling buttons as required
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Start") {
			(pf = new PrimeFinder()).execute();
			stopButton.setEnabled(true);
			startButton.setEnabled(false);
			resetButton.setEnabled(false);
		}else if(e.getActionCommand() == "Stop") {
			stopButton.setEnabled(false);
			startButton.setEnabled(true);
			resetButton.setEnabled(true);
			pf.cancel(true);
			pf = null;
		}else if(e.getActionCommand() == "Reset") {
			searchPoint.setText("0");
			foundPrimes.setText("");
			noFound.setText("0");
			nFound = 0;
		}

	}

	/* Because I want to report the integer I'm currently on
	 and the number of primes I've found, I need to package 
	 the two values into a single object. Note that I make the
	 fields public - this might be considered bad practise
	 but I'm confident this object is never going to be used
	 again */
	private class MyCounter {
		public int current;
		public int nFound;
		public MyCounter(int current,int nFound) {
			this.current = current;
			this.nFound = nFound;
		}
	}

	/* The worker that does the prime finding. Remember that all 
	   workers must implement doInBackground(), the other methods
	   are optional. Implement publish() and process() if you want
	   to do safe reporting */
	private class PrimeFinder extends SwingWorker<Void,MyCounter> {
		public Void doInBackground() {
			int current = Integer.parseInt(searchPoint.getText());
			while(!isCancelled()) {
				if(checkInteger(current)) {
					nFound++;
					foundPrimes.append(nFound + ": " + current + "\n");
				}
				publish(new MyCounter(current++,nFound));
			}
			return null;
		}
		// Displays the most recent current and nFound combination
		public void process(List<MyCounter> visits) {
			MyCounter lastVisit = visits.get(visits.size()-1);
			noFound.setText(""+lastVisit.nFound);
			searchPoint.setText(""+lastVisit.current);
		}
		/* Where I actually check for a prime. I kept this out of
		   the doInBackground() method for neatness. */
		public Boolean checkInteger(int toCheck) {
			if(toCheck<=1) {
				return false;
			}else {
				for(int i=2;i<=toCheck/2;i++) {
					if(toCheck%i == 0) {
						return false;
					}
				}
			}
			return true;
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Primes();
			}
		});
	}
}