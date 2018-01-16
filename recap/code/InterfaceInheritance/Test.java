public class Test {
	public static void main(String[] args) {
		TopClass tc = new TopClass();
		MyInterface m = new TopClass();
		NextClass nc = new NextClass();
		System.out.println(nc.add(1,3));

		// The big test!
		MyInterface m2 = new NextClass();
		m2.add(10,20);
	}
}