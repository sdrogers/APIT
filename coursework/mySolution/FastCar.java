import java.util.Random;
public class FastCar implements SpeedGenerator {
	private final Random r = new Random();
	public int getSpeed() {
		return r.nextInt(100);
	}
}