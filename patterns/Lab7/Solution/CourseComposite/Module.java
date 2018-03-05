public class Module implements CourseComponent {
	private String name;
	private Integer grade;
	public Module(String name,Integer grade) {
		this.name = name;
		this.grade = grade;
	}
	public Integer getGrade() {
		return grade;
	}
	public String toString() {
		String s = name + " (grade = " + grade + ")";
		return s;
	}
}