import java.util.ArrayList;

public class Composite implements Component {
	private ArrayList<Component> children = new ArrayList<Component>();
	public void add(Component a) {
		children.add(a);
	}
	public int getNumber() {
		int n = 0;
		for(Component c: children) {
			n += c.getNumber();
		}
		return n;
	}
	public int getPrice() {
		int n = 0;
		for(Component c: children) {
			n += c.getPrice();
		}
		return n;		
	}
}