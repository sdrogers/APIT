public class Dog implements MyElement{
	public Integer ageYears;
	public Dog(Integer a) {
		ageYears = a;
	}

	public void accept(MyVisitor visitor) {
		visitor.visit(this);
	}
}