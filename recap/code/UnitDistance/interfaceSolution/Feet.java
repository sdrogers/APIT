public class Feet implements DUnit {
	public double convertToSI(double value) {
		return value * 0.3048; // Number of meters in a foot!
	}
	public String displayString() {
		return "ft";
	}
}