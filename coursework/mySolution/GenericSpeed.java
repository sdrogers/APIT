import java.util.Random;
public class GenericSpeed implements SpeedGenerator {
	private final Random r = new Random();
	private final int min,max;
	public GenericSpeed(int min,int max) {
		this.min = min;
		this.max = max;
	}
	public int getSpeed() {
		return r.nextInt(this.max - this.min) + this.min;
	}
}