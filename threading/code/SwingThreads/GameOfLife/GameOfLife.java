import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.SwingUtilities;
import java.util.List;


public class GameOfLife extends JFrame implements ActionListener {
	
	private LifePanel lp;
	private LifeWorld lw;
	private LifeTask lt;
	private JButton startButton,stopButton,resetButton,randomButton;
	private JTextField randomField,startField,progressField;

	GameOfLife() {
		super("Game of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());

		startButton = makeButton("start");

		startField = makeField("100");

		stopButton = makeButton("stop");
		resetButton = makeButton("clear");
		randomButton = makeButton("random");

		randomField = makeField("0.2");

		progressField = makeField("0");
		progressField.setEditable(false);
		stopButton.setEnabled(false);

		int width = 50;

		lw = new LifeWorld(width);
		lw.randomise();
		lp = makePanel(width);

		pack();
		setVisible(true);
	}
	// Make the text fields
	private JTextField makeField(String initText) {
		JTextField a = new JTextField(5);
		a.setText(initText);
		getContentPane().add(a);
		return a;
	}
	// Make the panel
	private LifePanel makePanel(int width) {
		LifePanel a = new LifePanel(width);
		a.setPreferredSize(new Dimension(400,400));
		getContentPane().add(a);
		return a;
	}
	// Make the buttons
	private JButton makeButton(String caption) {
		JButton b = new JButton(caption);
		b.setActionCommand(caption);
		b.addActionListener(this);
		getContentPane().add(b);
		return b;
	}
	// Implement the action listener code
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "start") {
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			resetButton.setEnabled(false);
			randomButton.setEnabled(false);
			(lt = new LifeTask()).execute();
		}else if(e.getActionCommand() == "stop") {
			stopButton.setEnabled(false);
			startButton.setEnabled(true);
			resetButton.setEnabled(true);
			randomButton.setEnabled(true);
			lt.cancel(true);
			lt = null;
		}else if(e.getActionCommand() == "random") {
			Double probability = Double.parseDouble(randomField.getText());
			lw.setProbability(probability);
			lw.randomise();
			lp.repaint();
			progressField.setText("0");
		}else if(e.getActionCommand() == "clear") {
			lw.clear();
			lp.repaint();
			progressField.setText("0");
		}
	}
	// Create the worker that will run the task
	private class LifeTask extends SwingWorker<Void,Integer> {
		protected Void doInBackground() {
			try {
				Integer nSteps = Integer.parseInt(progressField.getText());
				Integer sleepTime = Integer.parseInt(startField.getText());
				while(!isCancelled()) {
					int a= lw.update(1);
					nSteps++;
					lp.repaint();
					if(a==0) {
						break;
					}
					publish(nSteps);
					Thread.sleep(sleepTime);
				}
			}catch(InterruptedException e) {

			}
			return null;
		}
		protected void process(List<Integer> reports) {
			Integer lastReport = reports.get(reports.size()-1);
			progressField.setText(""+lastReport);
		}
		protected void done() {
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			resetButton.setEnabled(true);
			randomButton.setEnabled(true);
		}
	}


	private class LifePanel extends JPanel implements MouseListener {
		private int width;
		LifePanel(int width) {
			super();
			this.width = width;
			addMouseListener(this);
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {
			int clickX = e.getX();
			int clickY = e.getY();
			int dX = getWidth()/width;
			int posX,posY;
			posX = clickX/dX;
			posY = clickY/dX;
			lw.flip(posX,posY);
			repaint();
		}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}

		protected void paintComponent(Graphics g) {
    		super.paintComponent(g);
      		Graphics2D g2 = (Graphics2D) g;
      		drawWorld(g2);
   		}
   		private void drawWorld(Graphics2D g2) {
   			int dX = this.getWidth()/width;
   			for(int i=0;i<width;i++) {
   				for(int j=0;j<width;j++) {
   					if(lw.get(i,j)==0) {
   						g2.setColor(Color.WHITE);
   					}else {
   						g2.setColor(Color.BLACK);
   					}
   					g2.fillRect(i*dX,j*dX,dX,dX);
					g2.setColor(Color.BLACK);
					g2.drawRect(i*dX,j*dX,dX,dX);
   				}
   			}
   		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GameOfLife();
			}
		});
	}

}