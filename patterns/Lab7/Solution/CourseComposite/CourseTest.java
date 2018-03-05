public class CourseTest {
	public static void main(String[] args) {
		CourseComponent maths = new Module("Maths",20);
		System.out.println(maths);
		CourseComponent english = new Module("English",30);

		Block it = new Block("IT");
		CourseComponent programming = new Module("Programming",50);
		CourseComponent databases = new Module("Databases",60);
		it.addComponent(programming);
		it.addComponent(databases);
		System.out.println(it);

		Block degree = new Block("Everything");
		degree.addComponent(it);
		degree.addComponent(maths);
		degree.addComponent(english);

		System.out.println(degree);
	}
}