import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingCounter extends JFrame implements ActionListener {
    private JTextField field;
    private JButton startButton,stopButton;
    private Counter counter;
    public SwingCounter() {
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(0,1));
        field = new JTextField("5",20);
        mainPanel.add(field);
        startButton = new JButton("Start");
        mainPanel.add(startButton);
        startButton.addActionListener(this);
        stopButton = new JButton("Stop");
        mainPanel.add(stopButton);
        stopButton.addActionListener(this);
        stopButton.setEnabled(false);
        this.add(mainPanel);
        this.setVisible(true);
    }

    private static class Counter extends SwingWorker<Void,Void> {
        private Integer value;
        private SwingCounter parent;
        public Counter(Integer start,SwingCounter parent) {
            this.value = start;
            this.parent = parent;
        }
        public Void doInBackground() {
            while(value > 0) {
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e) {
                    return null; // we get here when we cancel the worker
                }
                value --;
                parent.updateField(value);
            }
            return null;
        }
        public void done() {
            parent.resetButtons();
        }
    }
    public void resetButtons() {
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }
    public void updateField(Integer value) {
        field.setText(""+value);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) {
            //Start
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            counter = new Counter(Integer.parseInt(field.getText()),this);
            counter.execute();
        }else {
            // Stop
            stopButton.setEnabled(false);
            startButton.setEnabled(true);
            counter.cancel(true);
        }
    }

    public static void main(String[] args) {
        new SwingCounter();
    }
}