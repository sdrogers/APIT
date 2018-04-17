import java.util.Random;
public class Uniform implements WaitingTimeGenerator {
	private final Random r = new Random();
	private final int min,max;
	public Uniform(int min,int max) {
		this.min = min;
		this.max = max;
	}
	public int getTime() {
		return r.nextInt(max-min) + min;
	}
}