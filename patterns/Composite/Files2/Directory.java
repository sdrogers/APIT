import java.util.ArrayList;
public class Directory implements Component {
	private String name;
	private ArrayList<Component> children;
	public Directory(String name) {
		this.name = name;
		children = new ArrayList<Component>();
	}
	public void addChild(Component child) {
		children.add(child);
	}
	public int getSize() {
		int size = 0;
		for(Component c: children) {
			size += c.getSize();
		}
		return size;
	}
	public int getCount() {
		int count = 0;
		for(Component c: children) {
			count += c.getCount();
		}
		return count;
	}

	public String nicePrint(String prefix) {
		String output = prefix + name + ":";
		prefix += "\t";
		for(Component c: children) {
			output += "\n\t" + prefix + c.nicePrint(prefix);
		}
		return output;
	}
}