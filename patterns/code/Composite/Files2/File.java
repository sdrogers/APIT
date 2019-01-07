public class File implements Component {
	private String name;
	private int fileSize;
	public File(String name,int fileSize) {
		this.name = name;
		this.fileSize = fileSize;
	}
	public int getSize() {
		return fileSize;
	}
	public int getCount() {
		return 1;
	}
	public String nicePrint(String prefix) {
		return prefix + name + ": " + fileSize;
	}
}