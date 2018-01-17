import java.util.Arrays;

public class AbstractTest {
	public static void main(String[] args) {

		AbstractDistance a = new Yards(3.0);
		AbstractDistance b = new Inches(24.0);

		AbstractDistance[] d = new AbstractDistance[2];
		d[0] = a;
		d[1] = b;

		for(AbstractDistance c: d) {
			System.out.println(c.display());
		}

		Arrays.sort(d);

		System.out.println();
		System.out.println();

		for(AbstractDistance c: d) {
			System.out.println(c.display());
		}

	}
}