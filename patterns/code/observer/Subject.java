import java.util.ArrayList;

public class Subject {
	private Double[] data;
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	public Double[] getData() {
		return data;
	}
	public void setData(Double[] data) {
		this.data = data;
		this.notifyAllObservers();
	}
	public void attach(Observer observer) {
		observers.add(observer);
	}
	private void notifyAllObservers() {
		for(Observer observer : observers) {
			observer.notifyMe();
		}
	}
}