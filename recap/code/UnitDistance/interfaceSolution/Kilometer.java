public class Kilometer implements DUnit {
	public double convertToSI(double value) {
		return value * 1000.0;
	}
	public String displayString() {
		return "km";
	}
}