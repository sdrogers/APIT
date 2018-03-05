import java.util.GregorianCalendar;
public class CompAgeDaysVisitor implements MyVisitor {
	public void visit(Human human) {
		GregorianCalendar today = new GregorianCalendar();
		long diffSeconds = (today.getTimeInMillis() - human.dOB.getTimeInMillis())/1000;
		Integer ageDays = (int)diffSeconds/(60*60*24);
		System.out.println("This human is " + ageDays + " days old");	
	}
	public void visit(Dog dog) {
		Integer ageDays = dog.ageYears * 365;
		System.out.println("This dog is " + ageDays + " days old");
	}
}