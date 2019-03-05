public class SingleFile implements FSComponent {
	private int size;
	private String name;
	public SingleFile(String name,int size) {
		this.size = size;
		this.name = name;
	}
	public int getSize() {
		return size;
	}
	public int getNumber() {
		return 1;
	}
	public String toString() {
		return String.format("%s %6d",name,size);
	}
	public void display(String prefix) {
		System.out.println(prefix + this.toString());
	}
}