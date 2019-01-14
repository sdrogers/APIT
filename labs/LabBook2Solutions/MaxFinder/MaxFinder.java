public class MaxFinder {

    public static class MaxRow implements Runnable {
        Double[] row;
        int rowNumber;
        Double[] rowMaxes;
        public MaxRow(Double[] row,Double[] rowMaxes,int rowNumber) {
            this.row = row;
            this.rowMaxes = rowMaxes;
            this.rowNumber = rowNumber;
        }
        public void run() {
            rowMaxes[rowNumber] = 0.0;
            for(int i=0;i<row.length;i++) {
                if(row[i] > rowMaxes[rowNumber]) {
                    rowMaxes[rowNumber] = row[i];
                }
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
        Double[] rowMaxes = new Double[nRows];
        Thread[] threads = new Thread[nRows];
        for(int i =0;i<nRows;i++) {
            threads[i] = new Thread(new MaxRow(randArray[i],rowMaxes,i));
            threads[i].start();
        }
        try {
            for(int i=0;i<nRows;i++) {
                threads[i].join();
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        Double[] finalVal = new Double[1];
        Thread finalThread = new Thread(new MaxRow(rowMaxes, finalVal,0));
        finalThread.start();
        try {
            finalThread.join();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(finalVal[0]);
    }
}