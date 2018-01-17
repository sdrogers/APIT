import java.util.Arrays;

public class DistanceTest {
	public static void main(String[] args) {
		Meter m = new Meter();
		Kilometer km = new Kilometer();
		Mile mile = new Mile();
		Feet feet = new Feet();

		UnitDistance[] d = new UnitDistance[4];
		d[0] = new UnitDistance(1300,m);
		d[1] = new UnitDistance(1.2,km);
		d[2] = new UnitDistance(3.0,mile);
		d[3] = new UnitDistance(250.4,feet);

		for(UnitDistance a: d) {
			System.out.println(a);
		}

		System.out.println();


		// We can now sort these distances
		Arrays.sort(d);


		for(UnitDistance a: d) {
			System.out.println(a);
		}

	}
}