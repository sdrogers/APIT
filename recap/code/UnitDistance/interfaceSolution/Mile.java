public class Mile implements DUnit {
	public double convertToSI(double value) {
		return value * 1609.34; // Number of meters in a mile!
	}
	public String displayString() {
		return "miles";
	}
}