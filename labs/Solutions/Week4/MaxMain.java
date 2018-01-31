public class MaxMain {
	// This solution implements Runnable. A similar solution
	// could be made that extends Thread
	public static class MinMaxFinder implements Runnable {
		private Double[] myArray;
		private Double min,max;
		public MinMaxFinder(Double[] m) {
			myArray = m;
		}
		public void run() {
			// Find the min and max
			min = myArray[0];
			max = myArray[0];
			for(Double v: myArray) {
				if(v > max) {
					max = v;
				}
				if(v<min) {
					min = v;
				}
			}
		}
		// Methods that allow us to get the min and max values
		// for collating once finished
		public Double getMax() {
			return max;
		}
		public Double getMin() {
			return min;
		}
	}

	public static void main(String[] args) {
		int nRows = 100;
		int nCols = 50;
		
		// Make the data -- note that the method is static so we don't
		// need to make a MakeData object
		Double[][] d = MakeData.generateRandomData(nRows,nCols);

		MinMaxFinder[] finderArray = new MinMaxFinder[nRows];
		Thread[] threadArray = new Thread[nRows];


		// Make one finder and thread per row and start the thread
		for(int i=0;i<nRows;i++) {
			finderArray[i] = new MinMaxFinder(d[i]);
			threadArray[i] = new Thread(finderArray[i]);
			threadArray[i].start();
		}

		// Wait for them all to finish
		for(int i=0;i<nRows;i++) {
			try {
				threadArray[i].join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Once we get here, all threads have finished.
		// Find the global values
		Double globalMin = finderArray[0].getMin();
		Double globalMax = finderArray[1].getMax();
		for(int i=0;i<nRows;i++) {
			if(finderArray[i].getMin() < globalMin) {
				globalMin = finderArray[i].getMin();
			}
			if(finderArray[i].getMax() > globalMax) {
				globalMax = finderArray[i].getMax();
			}
		}

		System.out.println("Global min: " + globalMin);
		System.out.println("Global max: " + globalMax);


	}
}