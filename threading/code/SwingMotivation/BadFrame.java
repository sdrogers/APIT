import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BadFrame extends JFrame implements ActionListener{

	private JTextField numberField;
	private JButton startButton;

	public BadFrame() {
		this.setSize(500,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPanel = new JPanel();
		this.add(contentPanel);

		numberField = new JTextField("5",20);
		contentPanel.add(numberField);

		startButton = new JButton("Start");
		startButton.addActionListener(this);
		contentPanel.add(startButton);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		int number = Integer.parseInt(numberField.getText());
		for(int i = number;i >= 0;i--) {
			try {
				Thread.sleep(1000);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			numberField.setText(""+i);
		}
	}

	public static void main(String[] args) {
		new BadFrame();
	}
}