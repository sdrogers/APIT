public class ObserverTest {
	public static void main(String[] args) {
		Subject s = new Subject();
		Double[] d = new Double[5];
		d[0] = 1.0;d[1] = 1.2;d[2] = 1.4;d[3] = 1.7;d[4] = 2.4;
		new ListDataObserver(s);
		new MeanDataObserver(s);
		s.setData(d);
		d[3] = 3.2;
		s.setData(d);
	}
}