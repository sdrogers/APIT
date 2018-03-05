public class ProxyTest {
	public static void main(String[] args) {
		final Image image1 = new ProxyImage("the first photo");
		final Image image2 = new ProxyImage("the second photo");

		image1.displayImage(); // Only loads now
		image1.displayImage(); // doesn't need to load now
		
		// Image2 is never loaded as it isn't ever displated.
		// If we had used RealImage it would have been loaded
		// on creation.
	}
}