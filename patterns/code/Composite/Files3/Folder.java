import java.util.ArrayList;
public class Folder implements FSComponent {
	private ArrayList<FSComponent> children;
	private String name;
	public Folder(String name) {
		this.name = name;
		children = new ArrayList<FSComponent>();
	}
	public int getNumber() {
		int number = 1;
		for(FSComponent child: children) {
			number += child.getNumber();
		}
		return number;
	}
	public int getSize() {
		int size = 0;
		for(FSComponent child: children) {
			size += child.getSize();
		}
		return size;

	}
	public void add(FSComponent a) {
		children.add(a);
	}
	public String toString() {
		return this.name;
	}
	public void display(String prefix) {
		System.out.println(prefix + this.toString());
		prefix += "\t";
		for(FSComponent child:children) {
			child.display(prefix);
		}
	}
}