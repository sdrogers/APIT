public class MeanDataObserver extends Observer {
	public MeanDataObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	public void notifyMe() {
		Double[] data = subject.getData();
		Double mean = 0.0;
		for(int i=0;i<data.length;i++){
			mean += data[i];
		}
		mean = mean / (1.0*data.length);
		System.out.println();
		System.out.println("Mean: " + mean);
	}
}