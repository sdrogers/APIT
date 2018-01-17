// Class to store a distance with units
// The unit is any object that implements the DUnit interface
public class UnitDistance implements Comparable<UnitDistance> {
	private double distance;
	private DUnit unit;
	public UnitDistance(double distance,DUnit unit) {
		this.distance = distance;
		this.unit = unit;
	}
	public double getSI() {
		return this.unit.convertToSI(this.distance);
	}
	// compareTo method implements Comparable interface
	// Compares the standard unit value for each distance
	public int compareTo(UnitDistance other) {
		if(this.getSI() <= other.getSI()) {
			return -1;
		}else {
			return 1;
		}
	}
	public String toString() {
		return ""+this.distance + " " + this.unit.displayString();
	}
}