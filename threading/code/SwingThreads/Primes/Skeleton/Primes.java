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
			// This line creates your primefinder object and executes
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


	/* The worker that does the prime finding. Remember that all 
	   workers must implement doInBackground(), the other methods
	   are optional. Implement publish() and process() if you want
	   to do safe reporting */


	/* Note that this class has two type arguments. The first is the return
		from the doInBackground() method (Void) and the second is the type
		that I will send to publish */
	private class PrimeFinder extends SwingWorker<Void,XXXX> {
		public Void doInBackground() {
			// FILL THIS IN!
		}
		/* Displays the most recent current and nFound combination
			It is passed a list of ToPublish objects by java - you don't
			have to do this yourself! */
		public void process(List<XXXX> visits) {
				// Fill this in
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