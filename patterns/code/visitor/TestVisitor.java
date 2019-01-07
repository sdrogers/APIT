import java.util.Calendar;
import java.util.GregorianCalendar;
public class TestVisitor {
	public static void main(String[] args) {
		Dog d = new Dog(5);
		Calendar cal = new GregorianCalendar();
		cal.set(1995,5,12);
		Human h = new Human(cal);
		CompAgeDaysVisitor c = new CompAgeDaysVisitor();
		d.accept(c);
		h.accept(c);

		d.accept(new DisplayStuffVisitor());
		h.accept(new DisplayStuffVisitor());
	}
}