public class ProxyImage implements Image{
	private String filename = null;
	private RealImage image = null;
	public ProxyImage(final String filename) {
		this.filename = filename;
	}
	public void displayImage() {
		if (image == null) {
			image = new RealImage(filename);
		}
		image.displayImage();
	}
}