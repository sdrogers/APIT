public class ListDataObserver extends Observer {
	public ListDataObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	public void notifyMe() {
		Double[] data = subject.getData();
		System.out.println();
		System.out.println("Here's the data:");
		for(int i=0;i<data.length;i++) {
			System.out.println(data[i]);
		}
	}
}