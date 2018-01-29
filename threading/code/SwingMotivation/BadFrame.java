import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BadFrame extends JFrame implements ActionListener{

	private JTextField numberField;
	private JButton startButton;

	public BadFrame() {
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
		int number = Integer.parseInt(numberField.getText());
		for(int i = number;i >= 0;i--) {
			// Following code pauses for a second
			// needs to catch InterruptedExceptions
			// More on this later
			try {
				Thread.sleep(1000);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			// Set the text every second
			numberField.setText(""+i);
		}
	}

	public static void main(String[] args) {
		new BadFrame();
	}
}