public class Yards extends AbstractDistance {
	public Yards(double d) {
		super(d,"yards");
	}
	public double getSI() {
		return this.distance * 0.9144;
	}
}