import java.util.ArrayList;

public class Block implements CourseComponent {
	private String name;
	private ArrayList<CourseComponent> components;
	public Block(String name) {
		this.name = name;
		components = new ArrayList<CourseComponent>();
	}
	public void addComponent(CourseComponent component) {
		components.add(component);
	}
	public void removeComponent(CourseComponent component) {
		components.remove(component);
	}
	public Integer getGrade() {
		if(components.size()==0) {
			return 0;
		}else{
			Integer currentMax = 0;
			for(CourseComponent c: components) {
				if(c.getGrade()>currentMax) {
					currentMax = c.getGrade();
				}
			}
			return currentMax;
		}
	}
	public String toString() {
		String s = "[Block " + name + " (grade = " + this.getGrade() + ") including: ";
		for(CourseComponent c: components) {
			s += c + ";";
		}
		s += "]";
		return s;
	}
}