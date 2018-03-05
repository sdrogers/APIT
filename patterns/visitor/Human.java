import java.util.Calendar;
public class Human implements MyElement {
	public Calendar dOB;
	public Human(Calendar d) {
		dOB = d;
	}
	// All classes that implement MyElement have this method:
	public void accept(MyVisitor visitor) {
		visitor.visit(this);
	}
}