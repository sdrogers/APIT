import java.util.Random;

public class Exponential implements WaitingTimeGenerator {
	private final Random r = new Random();
	private final Double mean;
	public Exponential(Double mean) {
		this.mean = mean;
	}
	public int getTime() {
		Double u = r.nextDouble();
		return (int)(Math.log(1-u)/(-1.0*this.mean));
	}
}