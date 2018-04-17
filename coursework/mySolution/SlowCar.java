import java.util.Random;
public class SlowCar implements SpeedGenerator {
	private final Random r = new Random();
	public int getSpeed() {
		return 1000 + r.nextInt(1000);
	}
}