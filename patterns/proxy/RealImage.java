public class RealImage implements Image{
	private String filename = null;
	public RealImage(final String filename) {
		this.filename = filename;
		loadImageFromDisk();
	}
	private void loadImageFromDisk() {
		System.out.println("Loading " + filename);
	}
	public void displayImage() {
		System.out.println("Displaying " + filename);
	}
}