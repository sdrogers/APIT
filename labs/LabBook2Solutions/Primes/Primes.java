import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;


public class Primes extends JFrame implements ActionListener {
    
    public static class PrimeVal {
        private Integer number;
        private Integer prime;
        public PrimeVal(Integer number,Integer prime) {
            this.number = number;
            this.prime = prime;
        }
        public String toString() {
            return "" + number + ": " + prime;
        }
    }
    public static class PrimeWorker extends SwingWorker<Void,PrimeVal> {
        private Integer num = 2;
        private Integer pos = 1;
        private Primes parent;
        public PrimeWorker(Primes parent) {
            this.parent = parent;
        }
        public Void doInBackground() {
            while(true) {
                if(this.isCancelled()) {
                    return null;
                }
                if(isPrime(num)) {
                    publish(new PrimeVal(pos,num));
                    pos++;
                }
                num++;
            }
        }
        public void process(List<PrimeVal> plist) {
            System.out.println(plist.size());
            for(PrimeVal p: plist) {
                parent.addRow(p.toString());
            }
        }
        private boolean isPrime(Integer val) {
            if(val == 2) {
                return true;
            }
            if(val < 2) {
                return false;
            }
            for(int i=2;i<=val/2;i++) {
                if(val % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }


    private JButton startButton,stopButton;
    private JTextArea ta;
    private PrimeWorker worker;
    public Primes() {
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(0,1));
        startButton = new JButton("Start");
        mainPanel.add(startButton);
        stopButton = new JButton("Stop");
        mainPanel.add(stopButton);
        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        ta = new JTextArea(30, 20);
        mainPanel.add(new JScrollPane(ta));
        stopButton.setEnabled(false);
        this.add(mainPanel);
        this.setVisible(true);
    }
    public void addRow(String row) {
        ta.append(row + "\n");
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) {
            ta.setText("");
            worker = new PrimeWorker(this);
            worker.execute();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        }else {
            worker.cancel(true);
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }   
    
    public static void main(String[] args) {
        new Primes();
    }
}