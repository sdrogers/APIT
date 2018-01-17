public class Inches extends AbstractDistance {
	public Inches(double d) {
		super(d,"inches");
	}
	public double getSI() {
		return distance / 39.37;
	}
}