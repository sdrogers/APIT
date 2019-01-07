public class DisplayStuffVisitor implements MyVisitor {
	public void visit(Dog dog) {
		System.out.println("This is some stuff about dogs. They have 4 legs.");
	}
	public void visit(Human human) {
		System.out.println("This is some stuff about humans. They have 2 legs.");
	}
}