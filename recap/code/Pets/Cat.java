public class Cat extends AbstractPet {
	public Cat(String n, int a) {
		super(n,a);
	}
	public String description() {
		String output = name + " is a cat and is aged " + age;
		return output;
	}
}