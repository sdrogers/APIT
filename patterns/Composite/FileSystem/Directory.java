import java.util.ArrayList;
public class Directory implements FileComponent {
	private String name;
	private ArrayList<FileComponent> contents;
	public Directory(String n) {
		name = n;
		contents = new ArrayList<FileComponent>();
	}
	public void addComponent(FileComponent f) {
		contents.add(f);
	}
	public void removeComponent(FileComponent f) {
		contents.remove(f);
	}
	public Integer getSize() {
		Integer s = 0;
		for(FileComponent c: contents) {
			s += c.getSize();
		}
		return s;
	}
	public Integer getCount() {
		Integer s = 0;
		for(FileComponent c: contents) {
			s += c.getCount();
		}
		return s;
	}
	public String display(int tab_level) {
		String s = "\n";
		for(int i=0;i<tab_level;i++) {
			s += "\t";
		}
		s += name + " (" + this.getSize() + ")";
		for(FileComponent c: contents) {
			s += c.display(tab_level + 1);
		}
		return s;
	}
}