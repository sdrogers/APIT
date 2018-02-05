public class MergeSort {
	public static void main(String[] args) {
		// Nake some random
		int n_values = 1000;
		Double[] values = new Double[n_values];
		for(int i=0;i<n_values;i++) {
			values[i] = Math.random();
		}



		int n_threads = 10;
		int n_vals_per_thread = n_values/n_threads;
		ThreadSort[] sorters = new ThreadSort[n_threads];
		Thread[] threads = new Thread[n_threads];


		// Copy subsets of the array.
		// Note we could have used Arrays.copy instead of the loop
		int pos = 0;
		for(int i=0;i<n_threads;i++) {
			Double[] temp = new Double[n_vals_per_thread];
			for(int j=0;j<n_vals_per_thread;j++) {
				temp[j] = values[j+pos];
			}
			pos += n_vals_per_thread;
			// Make a new ThreadSort object (it implements Runnable)
			sorters[i] = new ThreadSort(temp);
			// Put it in a thread
			threads[i] = new Thread(sorters[i]);
			// Start the thread
			threads[i].start();
		}
		
		// Collect all of the results
		Double[][] subArrays = new Double[n_threads][n_vals_per_thread];
		for(int i=0;i<n_threads;i++) {
			try {
				threads[i].join();
			}catch(InterruptedException e){}
			subArrays[i] = sorters[i].getArray();
		}

		// Do the merge
		// pointers holds where we are in each list
		int[] pointers = new int[n_threads];
		Double[] sorted = new Double[n_values];
		for(int i=0;i<n_values;i++) {
			// find the lowest
			Double lowest = 1.0;
			int lowPos = -1;
			for(int j=0;j<n_threads;j++) {
				if(pointers[j] < n_vals_per_thread) {
					if(subArrays[j][pointers[j]] < lowest) {
						lowest = subArrays[j][pointers[j]];
						lowPos = j;
					}
				}
			}
			pointers[lowPos] += 1;
			sorted[i] = lowest;
		}
		for(int i=0;i<n_values;i++) {
			System.out.println(sorted[i]);
		}

	}
}