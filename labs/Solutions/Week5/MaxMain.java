	// Solution to week 5 lab. Similar to Week4 but threads using a shared object

	// This solution implements Runnable. A similar solution
	// could be made that extends Thread


	// The code has race conditions but they will only occur *very* infrequently
	// The worst kind of race conditions! The vast majority of times you run it, the values
	// produced by the threads (globalMM.getMin() and globalMM.getMax()) will match 
	// those produced by the for loops at the end of main.

	// To make them appear more often, uncomment the sleep code within the run method of 
	// MinMaxFinder. This will cause the max part of the code to go wrong more often and the 
	// numbers from the threads will not match the (correct) ones from the loops!



public class MaxMain {
	public static class MinMaxFinder implements Runnable {
		private Double[] myArray;
		private Double min,max;
		private MinMaxObject mm;
		public MinMaxFinder(Double[] m,MinMaxObject mm) {
			myArray = m;
			this.mm = mm;
		}

		// The run method can't be passed any parameters
		// so the row of the array needs to be a class attribute
		// it also can't return anything so we need getters (below)
		public void run() {
			// Find the min and max
			min = myArray[0];
			max = myArray[0];
			for(Double v: myArray) {
				if(v > mm.getMax()) {
					// try {
					// 	Thread.sleep(20);
					// }catch(InterruptedException e) {
					// 	e.printStackTrace();
					// }
					mm.setMax(v);
				}
				if(v < mm.getMin()) {
					mm.setMin(v);
				}
			}
		}


	}

	public static class MinMaxObject {
		// Object storing global min and max
		private double min;
		private double max;
		public MinMaxObject() {
			// Initialise the values
			min = 1.0;
			max = 0.0;
		}
		public double getMin() {
			return min;
		}
		public double getMax() {
			return max;
		}
		public void setMin(double min) {
			this.min = min;
		}
		public void setMax(double max) {
			this.max = max;
		}
	}




	public static void main(String[] args) {

		int nRows = 100;
		int nCols = 50;
		
		// Make the data -- note that the method is static so we don't
		// need to make a MakeData object, we just call the method
		Double[][] d = MakeData.generateRandomData(nRows,nCols);

		// Shared object that will store the global min and max
		MinMaxObject globalMM = new MinMaxObject();

		// Need to keep references to the MinMaxFinders to access their 
		// get methods later
		MinMaxFinder[] finderArray = new MinMaxFinder[nRows];

		// Need to keep references to the Threads to join them
		Thread[] threadArray = new Thread[nRows];


		// Make one finder and thread per row and start the thread
		for(int i=0;i<nRows;i++) {
			finderArray[i] = new MinMaxFinder(d[i],globalMM);
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
		System.out.println("Global min: " + globalMM.getMin());
		System.out.println("Global max: " + globalMM.getMax());


		// Test using some nested for loops
		double globalMin = 1.0;
		double globalMax = 0.0;
		for(int i=0;i<nRows;i++) {
			for(int j=0;j<nCols;j++) {
				if(d[i][j] <= globalMin) {
					globalMin = d[i][j];
				}
				if(d[i][j] >= globalMax) {
					globalMax = d[i][j];
				}
			}
		}

		// Once we get here, all threads have finished.
		System.out.println("Test Global min: " + globalMin);
		System.out.println("Test Global max: " + globalMax);



	}
}