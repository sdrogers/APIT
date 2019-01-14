public class MaxFinder4 {
    public static class MaxRow implements Runnable {
        Double[] row;
        SharedDoubleLock d;
        public MaxRow(Double[] row,SharedDoubleLock d) {
            this.row = row;
            this.d = d;
        }
        public void run() {
            for(int i=0;i<row.length;i++) {
                // d.lock();
                Double globalMax = d.getD();
                if(row[i] > globalMax) {
                    try {
                        Thread.sleep(1);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    d.setD(row[i]);
                }
                // d.unlock();
            }
        }
    }
    public static void main(String[] args) {
        int nRows = 100;
        int nCols = 50;
        Double[][] randArray = new Double[nRows][nCols];
        for(int r=0;r<nRows;r++) {
            for(int c=0;c<nCols;c++) {
                randArray[r][c] = Math.random();
            }
        }

        // Find the max using loops
        Double max = 0.0;
        for(int r=0;r<nRows;r++) {
            for(int c=0;c<nCols;c++) {
                if(randArray[r][c] > max) {
                    max = randArray[r][c];
                }
            }
        }
        System.out.println(max);

        // Threaded solution
        SharedDoubleLock d = new SharedDoubleLock();
        d.setD(0.0);
        Thread[] threads = new Thread[nRows];
        for(int i =0;i<nRows;i++) {
            threads[i] = new Thread(new MaxRow(randArray[i],d));
            threads[i].start();
        }
        try {
            for(int i=0;i<nRows;i++) {
                threads[i].join();
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(d.getD());
    }
}